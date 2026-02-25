package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.ExamResultQuestionTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamResultTestBuilder;
import com.jeffssousa.SimuladoApp.builders.QuestionTestBuilder;
import com.jeffssousa.SimuladoApp.entities.*;
import com.jeffssousa.SimuladoApp.repository.ExamQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultRepository;
import com.jeffssousa.SimuladoApp.repository.QuestionRepository;
import com.jeffssousa.SimuladoApp.service.ExamResultQuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamResultQuestionServiceTest {

    @Mock
    private ExamResultRepository examResultRepository;

    @Mock
    private ExamQuestionRepository examQuestionRepository;

    @Mock
    private ExamResultQuestionRepository examResultQuestionRepository;

    @InjectMocks
    private ExamResultQuestionService service;

    @Test
    @DisplayName("Deve criar a ordem das questions com sucesso")
    void shouldCreateAExamResultQuestionWithSuccess(){

        UUID examResultId = UUID.randomUUID();
        Long examId = 1L;
        ExamResult examResult = ExamResultTestBuilder
                .anExamResult()
                .withExamResultId(examResultId)
                .build();

        List<Question> listQuestion = List.of(
                    QuestionTestBuilder.aQuestion().build(),
                    QuestionTestBuilder.aQuestion().build()
                                            );

        List<ExamResultQuestion> list = List.of(
                                    ExamResultQuestionTestBuilder
                                            .anExamResultQuestion()
                                            .withExamResult(examResult)
                                            .withQuestion(listQuestion.getFirst())
                                            .withStatusAnswered()
                                            .build(),

                                    ExamResultQuestionTestBuilder
                                            .anExamResultQuestion()
                                            .withExamResult(examResult)
                                            .withQuestion(listQuestion.getLast())
                                            .withStatusAnswered()
                                            .build()
                                    );

        when(examResultRepository.findById(examResultId)).thenReturn(Optional.of(examResult));
        when(examQuestionRepository.findQuestionsByExamId(examId)).thenReturn(listQuestion);
        when(examResultQuestionRepository.saveAll(anyList())).thenReturn(list);


        List<ExamResultQuestion> savedList = service.createAll(examResultId, examId);

        verify(examResultRepository, times(1)).findById(examResultId);
        verify(examQuestionRepository, times(1)).findQuestionsByExamId(examId);
        verify(examResultQuestionRepository,times(1)).saveAll(anyList());

        assertNotNull(savedList);
        assertEquals(2,savedList.size());


    }


}
