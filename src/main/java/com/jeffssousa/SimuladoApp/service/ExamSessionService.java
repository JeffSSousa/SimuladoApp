package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.*;
import com.jeffssousa.SimuladoApp.repository.AlternativeRepository;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultRepository;
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

    public ExamResult start(ExamResult examResult, long examId) {

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

    private Question currentQuestion(List<ExamResultQuestion> questions) {
        return questions.stream()
                .filter(q -> q.getStatus().equals("Pending"))
                .sorted(Comparator.comparing(ExamResultQuestion::getSequence))
                .map(ExamResultQuestion::getQuestion)
                .findFirst()
                .orElse(null);
    }
}
