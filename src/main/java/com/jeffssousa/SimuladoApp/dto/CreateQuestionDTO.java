package com.jeffssousa.SimuladoApp.dto;

import java.util.List;

public record CreateQuestionDTO(
        Long examId,
        String description,
        String category,
        List<AlternativeRequestDTO> alternatives
    ) {
}
