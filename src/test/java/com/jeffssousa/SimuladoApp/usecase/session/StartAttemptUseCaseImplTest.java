package com.jeffssousa.SimuladoApp.usecase.session;


import com.jeffssousa.SimuladoApp.builders.ExamResultQuestionTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamResultTestBuilder;
import com.jeffssousa.SimuladoApp.builders.ExamTestBuilder;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.entities.ExamResultQuestion;
import com.jeffssousa.SimuladoApp.service.ExamResultQuestionService;
import com.jeffssousa.SimuladoApp.service.ExamSessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StartAttemptUseCaseImplTest {


    @Mock
    private ExamSessionService examSessionService;

    @Mock
    private ExamResultQuestionService examResultQuestionService;

    @InjectMocks
    private StartAttemptUseCaseImpl startAttemptUseCaseImpl;

    @Test
    @DisplayName("Deve iniciar a tentativa e salvar toda sequencia de questões")
    void shouldStartAttemptAndCreateAllQuestionSequence(){


        UUID examResultId = UUID.randomUUID();
        Long examId = 1L;

        ExamResult examResult = ExamResultTestBuilder
                                    .anExamResult()
                                    .withExamResultId(examResultId)
                                    .build();

        List<ExamResultQuestion> questionList = List.of(
                ExamResultQuestionTestBuilder.anExamResultQuestion().withExamResult(examResult).build(),
                ExamResultQuestionTestBuilder.anExamResultQuestion().withExamResult(examResult).build()
                                                    );


        when(examSessionService.start(examId)).thenReturn(examResult);
        when(examResultQuestionService.createAll(examResultId,examId)).thenReturn(questionList);


        ExamResult savedExamResult = startAttemptUseCaseImpl.startAttempt(examId);

        InOrder inOrder = Mockito.inOrder(examSessionService,examResultQuestionService);

        inOrder.verify(examSessionService).start(examId);
        inOrder.verify(examResultQuestionService).createAll(examResultId,examId);

        assertNotNull(savedExamResult);
        assertEquals(examResult.getExamResultId(),savedExamResult.getExamResultId());

    }


}
