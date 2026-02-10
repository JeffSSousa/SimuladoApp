package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository repository;

    public Exam save(Exam exam) {
        return repository.save(exam);
    }

    public Exam findById(long id) {

        return repository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Simulado não encontrado!"));

    }
}
