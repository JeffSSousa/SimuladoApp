package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.ExamResultTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultRepository;
import com.jeffssousa.SimuladoApp.service.ExamSessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamSessionServiceTest {

    @Mock
    private ExamResultRepository examResultRepository;

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamSessionService examSessionService;

    @Nested
    class startExam{

        @Test
        @DisplayName("Deve criar um ExamResult e iniciar o simulado/exame com sucesso")
        void shouldStartToExamWithSuccess(){

            Exam exam = ExamTestBuilder
                                    .anExam()
                                    .withExamId(1L)
                                    .build();

            ExamResult examResult = ExamResultTestBuilder
                                                    .anExamResult()
                                                    .withExam(exam)
                                                    .build();


            when(examRepository.findById(anyLong())).thenReturn(Optional.of(exam));
            when(examResultRepository.save(any(ExamResult.class))).thenReturn(examResult);

            ExamResult savedExamResult = examSessionService.start(examResult, 1L);

            verify(examRepository, times(1)).findById(anyLong());
            verify(examResultRepository, times(1)).save(any(ExamResult.class));

            assertNotNull(savedExamResult);
            assertEquals(examResult.getStatus(), savedExamResult.getStatus());
            assertNotNull(savedExamResult.getInitialTimestamp());
            assertNull(savedExamResult.getFinishedTimestamp());
            assertEquals(1,savedExamResult.getCurrentQuestion());

        }

    }

}
