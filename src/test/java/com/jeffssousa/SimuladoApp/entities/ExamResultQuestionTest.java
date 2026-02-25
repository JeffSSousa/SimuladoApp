package com.jeffssousa.SimuladoApp.entities;

import com.jeffssousa.SimuladoApp.builders.ExamResultQuestionTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamResultQuestionTest {

    @Test
    @DisplayName("Deve alterar o status para PENDING com sucesso")
    void shouldAlterStatusForPendingWithSuccess(){

        ExamResultQuestion examResultQuestion = ExamResultQuestionTestBuilder
                .anExamResultQuestion()
                .withStatusAnswered()
                .build();

        examResultQuestion.withStatusPending();

        assertEquals("PENDING",examResultQuestion.getStatus());

    }
}
