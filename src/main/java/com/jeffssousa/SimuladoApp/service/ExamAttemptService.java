package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.dto.AlternativeResponseDTO;
import com.jeffssousa.SimuladoApp.dto.AnswerQuestionDTO;
import com.jeffssousa.SimuladoApp.dto.QuestionAlternativeResponseDTO;
import com.jeffssousa.SimuladoApp.entities.*;
import com.jeffssousa.SimuladoApp.mapper.AlternativeMapper;
import com.jeffssousa.SimuladoApp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamAttemptService {

    private final ExamResultRepository examResultRepository;

    private final ExamRepository examRepository;

    private final ExamResultQuestionRepository examResultQuestionRepository;

    private final AlternativeRepository alternativeRepository;

    private final QuestionRepository questionRepository;

    private final UserAnswerRepository userAnswerRepository;

    private final AlternativeMapper alternativeMapper;

    public ExamResult start(long examId) {

        ExamResult examResult  = new ExamResult();

        Exam exam = examRepository.findById(examId)
                            .orElseThrow(() -> new EntityNotFoundException("Simulado não encontrado!"));

        examResult.setExam(exam);
        examResult.start(10);

        return examResultRepository.save(examResult);
    }

    public QuestionAlternativeResponseDTO getCurrentQuestion(UUID examResultId) {

        ExamResult examResult = examResultRepository.findById(examResultId)
                .orElseThrow(() -> new EntityNotFoundException("Tentativa não foi encontrada"));

        List<ExamResultQuestion> questions = examResultQuestionRepository.findAllByExamResult(examResult);

        Question question = currentQuestion(questions);

        List<Alternative> alternatives = alternativeRepository.findAllByQuestion(question);


        return new QuestionAlternativeResponseDTO(
                    question.getQuestionId(),
                    question.getDescription(),
                    generateAlternativesDTO(alternatives)
        );
    }

    @Transactional
    public UserAnswer answerQuestion(AnswerQuestionDTO dto) {

        ExamResult examResult = examResultRepository.findById(dto.examResultId())
                .orElseThrow(() -> new EntityNotFoundException("Tentativa não encontrada"));

        Question question = questionRepository.findById(dto.questionId())
                .orElseThrow(() -> new EntityNotFoundException("Questão não foi encontrada"));

        Alternative alternative = alternativeRepository.findById(dto.alternativeId())
                .orElseThrow(() -> new EntityNotFoundException("Alternativa não encontrada"));


        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setExamResult(examResult);
        userAnswer.setQuestion(question);
        userAnswer.setAlternative(alternative);

        int currentQuestion = examResult.getCurrentQuestion();
        examResult.setCurrentQuestion(++currentQuestion);
        examResultRepository.save(examResult);

        return userAnswerRepository.save(userAnswer);

        //adicionar metodo que marca como respondido

    }

    private Question currentQuestion(List<ExamResultQuestion> questions) {
        return questions.stream()
                .filter(q -> q.getStatus().equals("PENDING"))
                .sorted(Comparator.comparing(ExamResultQuestion::getSequence))
                .map(ExamResultQuestion::getQuestion)
                .findFirst()
                .orElse(null);
    }

    private List<AlternativeResponseDTO> generateAlternativesDTO(List<Alternative> alternatives){
        return alternatives
                .stream()
                .map(alternativeMapper::toDTO)
                .toList();
    }
}
