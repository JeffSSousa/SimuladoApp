package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.ExamQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamQuestionService {

    private final ExamQuestionRepository repository;

    private final ExamRepository examRepository;

    private final QuestionRepository questionRepository;


    public ExamQuestion save(ExamQuestion examQuestion, Long examId, UUID questionId) {

        Exam exam = examRepository.findById(examId)
                                    .orElseThrow(() -> new EntityNotFoundException("Simulado não encontrado!"));

        Question question = questionRepository.findById(questionId)
                                    .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada!"));

        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);

        return repository.save(examQuestion);
    }

}
