package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.AlternativeTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamTestBuilder;
import com.jeffssousa.SimuladoApp.builders.QuestionTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.AlternativeRepository;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import com.jeffssousa.SimuladoApp.service.AlternativeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlternativeTest {

    @Mock
    private AlternativeRepository repository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private AlternativeService alternativeService;

    @Nested
    class save{

        @Test
        @DisplayName("Deve criar todas as alternativas com sucesso")
        void shouldCreateAllAlternativesWithSuccess(){

            UUID questionId = UUID.randomUUID();
            Question question = QuestionTestBuilder
                    .aQuestion()
                    .withQuestionId(questionId)
                    .build();


            List<Alternative> list = Arrays.asList(
                    AlternativeTestBuilder.anAlternative().withDescription("Manaus").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Rio de Janeiro").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("São Paulo").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Fortaleza").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Brasilia").correct().build()
            );

            when(repository.saveAll(anyList())).thenReturn(list);
            when(questionRepository.findById(any(UUID.class))).thenReturn(Optional.of(question));

            List<Alternative> savedList = alternativeService.saveAll(list, questionId);

            verify(repository,times(1)).saveAll(anyList());

            assertNotNull(savedList);
            assertEquals(list.getFirst().getDescription(),savedList.getFirst().getDescription());
            assertEquals(list.size(),savedList.size());
            assertEquals(list.get(4).isCorrect(), savedList.get(4).isCorrect());
            assertEquals(
                        questionId,
                        savedList.getFirst().getQuestion().getQuestionId()
                        );
        }


    }

}
