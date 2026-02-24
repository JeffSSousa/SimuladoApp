package com.jeffssousa.SimuladoApp.usecase.question;

import com.jeffssousa.SimuladoApp.builders.AlternativeTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamQuestionTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamTestBuilder;
import com.jeffssousa.SimuladoApp.builders.QuestionTestBuilder;
import com.jeffssousa.SimuladoApp.dto.AlternativeRequestDTO;
import com.jeffssousa.SimuladoApp.dto.CreateQuestionDTO;
import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.mapper.QuestionAlternativeMapper;
import com.jeffssousa.SimuladoApp.service.AlternativeService;
import com.jeffssousa.SimuladoApp.service.ExamQuestionService;
import com.jeffssousa.SimuladoApp.service.ExamService;
import com.jeffssousa.SimuladoApp.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateQuestionUseCaseImplTest {


    @Mock
    private QuestionService questionService;

    @Mock
    private ExamQuestionService examQuestionService;

    @Mock
    private AlternativeService alternativeService;

    @Mock
    private ExamService examService;

    @Mock
    private QuestionAlternativeMapper mapper;

    @InjectMocks
    private CreateQuestionUseCaseImpl useCase;

    @Captor
    private ArgumentCaptor<ExamQuestion> examQuestionCaptor;

    @Nested
    class createQuestionWithAlternativesAndLinkedExam{


        @Test
        @DisplayName("Deve criar uma Questão com Alternativas e criar ExamQuestion com sucesso")
        void shouldCreateQuestionWithAlternativesAndLinkToExamSuccessfully(){

            Exam exam = ExamTestBuilder.anExam().withExamId(1L).build();
            Question question = QuestionTestBuilder.aQuestion().build();

            ExamQuestion examQuestion = ExamQuestionTestBuilder
                                        .anExamQuestion()
                                        .withQuestion(question)
                                        .withExam(exam)
                                        .build();

            List<Alternative> list = Arrays.asList(
                    AlternativeTestBuilder.anAlternative().withDescription("Manaus").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Rio de Janeiro").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("São Paulo").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Fortaleza").build(),
                    AlternativeTestBuilder.anAlternative().withDescription("Brasilia").correct().build()
            );

            List<AlternativeRequestDTO> listDto = new ArrayList<>();
            list.forEach(a -> listDto.add(
                            new AlternativeRequestDTO(
                                        a.getDescription(),
                                        a.isCorrect())
                            )
                        );

            CreateQuestionDTO dto = new CreateQuestionDTO(
                    exam.getExamId(),
                    question.getDescription(),
                    question.getCategory(),
                    listDto
            );

            when(questionService.save(any(Question.class))).thenReturn(question);
            when(examService.findById(anyLong())).thenReturn(exam);

            when(examQuestionService.save(
                                    any(ExamQuestion.class))
                                    ).thenReturn(examQuestion);

            when(alternativeService.saveAll(
                                    anyList(),
                                    eq(question.getQuestionId())
                                    )).thenReturn(list);

            when(mapper.toEntity(any(CreateQuestionDTO.class))).thenReturn(question);

            Question savedQuestion = useCase.createQuestionForExam(dto);


            InOrder inOrder = inOrder(questionService, examService, examQuestionService, alternativeService);

            inOrder.verify(questionService).save(any(Question.class));
            inOrder.verify(examService).findById(anyLong());
            inOrder.verify(examQuestionService).save(any(ExamQuestion.class));
            inOrder.verify(alternativeService).saveAll(
                                                        anyList(),
                                                        eq(question.getQuestionId())
                                                        );


            assertNotNull(savedQuestion);
            assertEquals(question.getDescription(), savedQuestion.getDescription());
            assertEquals(question.getCategory(), savedQuestion.getCategory());

        }


    }



}
