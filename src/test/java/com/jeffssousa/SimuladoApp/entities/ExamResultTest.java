package com.jeffssousa.SimuladoApp.entities;

import com.jeffssousa.SimuladoApp.builders.ExamResultTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ExamResultTest {

    @Test
    @DisplayName("Deve iniciar um ExamResult com sucesso")
    void shouldStartExamWithSuccess(){

        int totalQuestions = 10;
        ExamResult examResult = ExamResultTestBuilder.anExamResult().build();

        examResult.start(totalQuestions);

        assertEquals("IN_PROGRESS", examResult.getStatus());
        assertNotNull(Instant.now());
        assertEquals(1,examResult.getCurrentQuestion());
        assertEquals(totalQuestions, examResult.getTotalQuestion());
        assertEquals(0,examResult.getCorrectAnswers());
        assertNull(examResult.getFinishedTimestamp());
    }

}
