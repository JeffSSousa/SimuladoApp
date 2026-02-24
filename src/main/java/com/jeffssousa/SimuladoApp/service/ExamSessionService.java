package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.*;
import com.jeffssousa.SimuladoApp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamSessionService {

    private final ExamResultRepository examResultRepository;

    private final ExamRepository examRepository;

    private final ExamResultQuestionRepository examResultQuestionRepository;

    private final AlternativeRepository alternativeRepository;

    private final QuestionRepository questionRepository;

    private final UserAnswerRepository userAnswerRepository;

    public ExamResult start(long examId) {

        ExamResult examResult  = new ExamResult();

        Exam exam = examRepository.findById(examId)
                            .orElseThrow(() -> new EntityNotFoundException("Simulado não encontrado!"));

        examResult.setExam(exam);
        examResult.start(10);

        return examResultRepository.save(examResult);
    }

    public Question getCurrentQuestion(UUID examResultId) {

        ExamResult examResult = examResultRepository.findById(examResultId)
                .orElseThrow(() -> new EntityNotFoundException("Tentativa não foi encontrada"));

        List<ExamResultQuestion> questions = examResultQuestionRepository.findAllByExamResult(examResult);

        Question question = currentQuestion(questions);

        List<Alternative> alternatives = alternativeRepository.findAllByQuestion(question);

        return question;
    }

    public UserAnswer answerQuestion(UUID examResultId, UUID questionId, UUID alternativeId) {

        ExamResult examResult = examResultRepository.findById(examResultId)
                .orElseThrow(() -> new EntityNotFoundException("Tentativa não encontrada"));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Questão não foi encontrada"));

        Alternative alternative = alternativeRepository.findById(alternativeId)
                .orElseThrow(() -> new EntityNotFoundException("Alternativa não encontrada"));


        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setExamResult(examResult);
        userAnswer.setQuestion(question);
        userAnswer.setAlternative(alternative);
        return userAnswerRepository.save(userAnswer);

        //adicionar metodo mais um
        //adicionar metodo que marca como respondido

    }

    private Question currentQuestion(List<ExamResultQuestion> questions) {
        return questions.stream()
                .filter(q -> q.getStatus().equals("Pending"))
                .sorted(Comparator.comparing(ExamResultQuestion::getSequence))
                .map(ExamResultQuestion::getQuestion)
                .findFirst()
                .orElse(null);
    }
}
