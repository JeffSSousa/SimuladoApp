package com.jeffssousa.SimuladoApp.services;

import com.jeffssousa.SimuladoApp.builders.*;
import com.jeffssousa.SimuladoApp.dto.AlternativeResponseDTO;
import com.jeffssousa.SimuladoApp.dto.AnswerQuestionDTO;
import com.jeffssousa.SimuladoApp.dto.QuestionAlternativeResponseDTO;
import com.jeffssousa.SimuladoApp.entities.*;
import com.jeffssousa.SimuladoApp.mapper.AlternativeMapper;
import com.jeffssousa.SimuladoApp.repository.*;
import com.jeffssousa.SimuladoApp.service.ExamAttemptService;
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
public class ExamAttemptServiceTest {

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

    @Mock
    private UserAnswerRepository userAnswerRepository;

    @Mock
    private AlternativeMapper alternativeMapper;

    @InjectMocks
    private ExamAttemptService examAttemptService;

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

            ExamResult savedExamResult = examAttemptService.start(1L);

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

            List<AlternativeResponseDTO> alternatives = new ArrayList<>();
            list.forEach(a -> alternatives.add(
                                        new AlternativeResponseDTO(a.getAlternativeId(),
                                                                    a.getDescription()
                                        ))
                        );

            QuestionAlternativeResponseDTO dto = new QuestionAlternativeResponseDTO(
                    question.getQuestionId(),
                    question.getDescription(),
                    alternatives
                );

            when(alternativeMapper.toDTO(any(Alternative.class))).thenReturn(
                    new AlternativeResponseDTO(alternatives.getFirst().alternativeId(), "Manaus"),
                    new AlternativeResponseDTO(alternatives.get(1).alternativeId(),"Rio de Janeiro"),
                    new AlternativeResponseDTO(alternatives.get(2).alternativeId(),"São Paulo"),
                    new AlternativeResponseDTO(alternatives.get(3).alternativeId(),"Fortaleza"),
                    new AlternativeResponseDTO(alternatives.getLast().alternativeId(),"Brasilia")
            );
            when(examResultRepository.findById(examResultId)).thenReturn(Optional.of(examResult));
            when(examResultQuestionRepository.findAllByExamResult(examResult)).thenReturn(questionList);
            when(alternativeRepository.findAllByQuestion(question)).thenReturn(list);


            QuestionAlternativeResponseDTO responseDTO = examAttemptService.getCurrentQuestion(examResultId);


            verify(examResultRepository, times(1)).findById(examResultId);
            verify(examResultQuestionRepository, times(1)).findAllByExamResult(examResult);
            verify(alternativeRepository, times(1)).findAllByQuestion(question);

            assertNotNull(responseDTO);
            assertEquals(question.getDescription(), responseDTO.description());
            assertEquals(question.getQuestionId(),responseDTO.questionId());
            assertEquals(alternatives.size(),responseDTO.alternatives().size());
            assertEquals(alternatives.getFirst(),responseDTO.alternatives().getFirst());
            assertEquals(alternatives.getLast(),responseDTO.alternatives().getLast());

        }

    }

    @Nested
    class answerCurrentQuestion{

        @Test
        @DisplayName("Deve Responder a questão atual da tentativa com sucesso")
        void shouldAnswerCurrentQuestionWithSuccess(){

            // acrescentar mais para o status atual
            // finalizar


            UUID examResultId = UUID.randomUUID();
            UUID alternativeId = UUID.randomUUID();
            UUID questionId = UUID.randomUUID();

            ExamResult examResult = ExamResultTestBuilder
                    .anExamResult()
                    .withExamResultId(examResultId)
                    .build();

            Question question = QuestionTestBuilder
                    .aQuestion()
                    .withQuestionId(questionId)
                    .build();


            Alternative alternative = AlternativeTestBuilder
                    .anAlternative()
                    .withAlternativeId(alternativeId)
                    .build();


            UserAnswer userAnswer = UserAnswerTestBuilder
                    .builder()
                    .withExamResult(examResult)
                    .withQuestion(question)
                    .withAlternative(alternative)
                    .build();

            AnswerQuestionDTO dto = new AnswerQuestionDTO(
                    examResultId,
                    questionId,
                    alternativeId
            );

            when(examResultRepository.findById(examResultId)).thenReturn(Optional.of(examResult));
            when(alternativeRepository.findById(alternativeId)).thenReturn(Optional.of(alternative));
            when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
            when(userAnswerRepository.save(any(UserAnswer.class))).thenReturn(userAnswer);


            UserAnswer savedUserAnswer = examAttemptService.answerQuestion(dto);

            assertNotNull(savedUserAnswer);
            assertEquals(userAnswer.getQuestion().getDescription(),savedUserAnswer.getQuestion().getDescription());

        }

    }

}
