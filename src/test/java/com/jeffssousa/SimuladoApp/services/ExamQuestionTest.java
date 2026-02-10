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

            ExamQuestion savedExamQuestion = examQuestionService.save(examQuestion);

            verify(examQuestionRepository, times(1)).save(any(ExamQuestion.class));

            assertNotNull(savedExamQuestion);
            assertEquals(exam.getExamId(),savedExamQuestion.getExam().getExamId());
            assertEquals(question.getQuestionId(), savedExamQuestion.getQuestion().getQuestionId());

        }

    }

}
