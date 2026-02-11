package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamSessionService {

    private final ExamResultRepository examResultRepository;

    private final ExamRepository examRepository;

    public ExamResult start(ExamResult examResult, long examId) {

        Exam exam = examRepository.findById(examId)
                            .orElseThrow(() -> new EntityNotFoundException("Simulado não encontrado!"));

        examResult.setExam(exam);
        examResult.start(10);

        return examResultRepository.save(examResult);
    }

}
