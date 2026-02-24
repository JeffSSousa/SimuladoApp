package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.QuestionTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import com.jeffssousa.SimuladoApp.service.QuestionService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private QuestionService service;


    @Nested
    class save{

        @Test
        @DisplayName("Deve salvar uma Questão com sucesso")
        void shouldCreateAQuestionWithSuccess(){

            Question question = QuestionTestBuilder.aQuestion().build();

            when(repository.save(any(Question.class))).thenReturn(question);

            Question savedQuestion = service.save(question);

            verify(repository, times(1)).save(any(Question.class));

            assertEquals(savedQuestion.getDescription(), question.getDescription());
            assertEquals(savedQuestion.getCategory(), question.getCategory());

        }

    }

    @Nested
    class findById{

        @Test
        @DisplayName("Deve Buscar Questão pelo id com sucesso")
        void shouldFindByIdQuestionWithSuccess(){

            Question question = QuestionTestBuilder.aQuestion()
                    .withQuestionId(UUID.randomUUID())
                    .build();

            when(repository.findById(any(UUID.class))).thenReturn(Optional.of(question));

            Question returnedQuestion = service.findById(question.getQuestionId());

            verify(repository, times(1)).findById(any(UUID.class));

            assertNotNull(returnedQuestion);
            assertEquals(question.getDescription(),returnedQuestion.getDescription());
            assertEquals(question.getCategory(),returnedQuestion.getCategory());

        }

        @Test
        @DisplayName("Deve retornar uma exceção caso a Questão não exista")
        void shouldThrowExceptionWhenQuestionNotFound(){

            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

            EntityNotFoundException e = assertThrows(
                    EntityNotFoundException.class,
                    () -> service.findById(UUID.randomUUID())
            );

            verify(repository, times(1)).findById(any(UUID.class));
            assertEquals("Questão não encontrada!", e.getMessage());
        }

    }

}
