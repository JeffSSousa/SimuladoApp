package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository repository;

    public Exam save(Exam exam) {
        return repository.save(exam);
    }
}
