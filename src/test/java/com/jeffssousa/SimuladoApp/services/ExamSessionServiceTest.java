package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.*;
import com.jeffssousa.SimuladoApp.entities.*;
import com.jeffssousa.SimuladoApp.repository.*;
import com.jeffssousa.SimuladoApp.service.ExamQuestionService;
import com.jeffssousa.SimuladoApp.service.ExamSessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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

    @Mock
    private ExamResultQuestionRepository examResultQuestionRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AlternativeRepository alternativeRepository;

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

    @Nested
    class getCurrentQuestion{

        @Test
        @DisplayName("Deve buscar a questão atual com sucesso")
        void shouldFindCurrentQuestionWithSuccess(){

            UUID examResultId = UUID.randomUUID();
            Long examId = 1L;
            ExamResult examResult = ExamResultTestBuilder
                                    .anExamResult()
                                    .withExamResultId(examResultId)
                                    .build();

            Question question = QuestionTestBuilder.aQuestion().withQuestionId(UUID.randomUUID()).build();

            List<ExamResultQuestion> questionList = List.of(
                    ExamResultQuestionTestBuilder.anExamResultQuestion().withExamResult(examResult).withStatusAnswered().build(),
                    ExamResultQuestionTestBuilder.anExamResultQuestion().withExamResult(examResult).withQuestion(question).withStatusPending().build()
            );

            List<Alternative> list = Arrays.asList(
                    AlternativeTestBuilder.anAlternative().withDescription("Manaus").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Rio de Janeiro").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("São Paulo").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Fortaleza").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Brasilia").correct().build()
            );


            when(examResultRepository.findById(examResultId)).thenReturn(Optional.of(examResult));
            when(examResultQuestionRepository.findAllByExamResult(examResult)).thenReturn(questionList);
            when(questionRepository.findById(question.getQuestionId())).thenReturn(Optional.of(question));
            when(alternativeRepository.findAllByQuestion(question)).thenReturn(list);


            Question savedQuestion = examSessionService.getCurrentQuestion(examResultId);


            verify(examResultRepository, times(1)).findById(examResultId);
            verify(examResultQuestionRepository, times(1)).findAllByExamResult(examResult);
            verify(questionRepository, times(1)).findById(question.getQuestionId());
            verify(alternativeRepository, times(1)).findAllByQuestion(question);

            assertNotNull(savedQuestion);
            assertEquals(question.getQuestionId(),savedQuestion.getQuestionId());

        }

    }

}
