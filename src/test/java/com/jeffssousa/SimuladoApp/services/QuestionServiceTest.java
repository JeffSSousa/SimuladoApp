package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.QuestionTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import com.jeffssousa.SimuladoApp.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
