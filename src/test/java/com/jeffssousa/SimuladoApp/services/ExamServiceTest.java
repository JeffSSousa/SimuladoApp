package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.ExamTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import com.jeffssousa.SimuladoApp.service.ExamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {


    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamService examService;

    @Nested
    class createExam{

        @Test
        @DisplayName("Deve criar um Simulado/Exame com sucesso")
        void shouldCreateAExamWithSuccess(){

            Exam exam = ExamTestBuilder.anExam().build();
            when(examRepository.save(any(Exam.class))).thenReturn(exam);


            Exam salvedExam = examService.save(exam);


            verify(examRepository, times(1)).save(any(Exam.class));

            assertNotNull(salvedExam);
            assertEquals(salvedExam.getCategory(), exam.getCategory());
        }

    }

}
