package com.jeffssousa.SimuladoApp.dto;

import java.util.UUID;

public record AnswerQuestionDTO(
    UUID examResultId,
    UUID questionId,
    UUID alternativeId
    ){
}
