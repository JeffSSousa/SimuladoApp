package com.jeffssousa.SimuladoApp.dto;

import java.util.UUID;

public record AlternativeResponseDTO(
        UUID alternativeId,
        String description
    ) {
}
