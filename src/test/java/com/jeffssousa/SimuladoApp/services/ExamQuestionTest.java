package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.ExamQuestionTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamTestBuilder;
import com.jeffssousa.SimuladoApp.builders.QuestionTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.ExamQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamRepository;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import com.jeffssousa.SimuladoApp.service.ExamQuestionService;
import com.jeffssousa.SimuladoApp.service.ExamService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamQuestionTest {

    @Mock
    private ExamQuestionRepository examQuestionRepository;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private ExamQuestionService examQuestionService;


    @Nested
    class save{

        @Test
        @DisplayName("Deve criar um examQuestion com sucesso")
        void shouldCreateAExamQuestion(){

            Exam exam = ExamTestBuilder
                                    .anExam()
                                    .withExamId(1L)
                                    .build();

            Question question = QuestionTestBuilder
                                    .aQuestion()
                                    .withQuestionId(UUID.randomUUID())
                                    .build();

            ExamQuestion examQuestion = ExamQuestionTestBuilder
                                                        .anExamQuestion()
                                                        .withExam(exam)
                                                        .withQuestion(question)
                                                        .build();


            when(examQuestionRepository.save(any(ExamQuestion.class))).thenReturn(examQuestion);
            when(examRepository.findById(anyLong())).thenReturn(Optional.of(exam));
            when(questionRepository.findById(any(UUID.class))).thenReturn(Optional.of(question));

            ExamQuestion savedExamQuestion = examQuestionService.save(examQuestion, 1L, question.getQuestionId());

            verify(examQuestionRepository, times(1)).save(any(ExamQuestion.class));
            verify(examRepository, times(1)).findById(anyLong());
            verify(questionRepository, times(1)).findById(any(UUID.class));

            assertNotNull(savedExamQuestion);
            assertEquals(exam.getExamId(),savedExamQuestion.getExam().getExamId());
            assertEquals(question.getQuestionId(), savedExamQuestion.getQuestion().getQuestionId());

        }

        @Test
        @DisplayName("Deve retornar uma exceção caso o Simulado/Exame não exista")
        void shouldThrowExceptionWhenExamNotFound(){

            when(examRepository.findById(anyLong())).thenReturn(Optional.empty());

            EntityNotFoundException e = assertThrows(
                    EntityNotFoundException.class,
                    () -> examQuestionService.save(any(ExamQuestion.class), 1L, UUID.randomUUID())
            );

            verify(examRepository, times(1)).findById(anyLong());
            assertEquals("Simulado não encontrado!", e.getMessage());
        }

        @Test
        @DisplayName("Deve retornar uma exceção caso a Questão não exista")
        void shouldThrowExceptionWhenQuestionNotFound(){

            Exam exam = ExamTestBuilder.anExam().build();

            when(questionRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
            when(examRepository.findById(anyLong())).thenReturn(Optional.of(exam));

            EntityNotFoundException e = assertThrows(
                    EntityNotFoundException.class,
                    () -> examQuestionService.save(any(ExamQuestion.class), 1L, UUID.randomUUID())
            );

            verify(questionRepository, times(1)).findById(any(UUID.class));
            assertEquals("Questão não encontrada!", e.getMessage());
        }

    }

}
