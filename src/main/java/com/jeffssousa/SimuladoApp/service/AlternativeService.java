package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.AlternativeRepository;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlternativeService {

    private final AlternativeRepository alternativeRepository;

    private final QuestionRepository questionRepository;


    public List<Alternative> saveAll(List<Alternative> alternatives, UUID questionId) {

        if(!questionRepository.existsById(questionId)){
            throw new EntityNotFoundException("Questão não encontrada!");
        }

        return alternativeRepository.saveAll(alternatives);
    }

}
