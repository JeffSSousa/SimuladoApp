package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public Question save(Question question) {
        return repository.save(question);
    }

    public Question findById(UUID questionId) {

        return repository.findById(questionId)
                                        .orElseThrow(() -> new EntityNotFoundException("Questão não encontrada!"));
    }
}
