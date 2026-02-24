package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.repository.ExamQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExamQuestionService {

    private final ExamQuestionRepository repository;

    public ExamQuestion save(ExamQuestion examQuestion) {
        return repository.save(examQuestion);
    }

}
