package com.jeffssousa.SimuladoApp.dto;

import java.util.List;
import java.util.UUID;

public record QuestionAlternativeResponseDTO(
        UUID questionId,
        String description,
        List<AlternativeResponseDTO> alternatives
    ) {
}
