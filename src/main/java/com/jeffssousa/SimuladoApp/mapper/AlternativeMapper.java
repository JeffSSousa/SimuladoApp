package com.jeffssousa.SimuladoApp.mapper;

import com.jeffssousa.SimuladoApp.dto.AlternativeResponseDTO;
import com.jeffssousa.SimuladoApp.entities.Alternative;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlternativeMapper {

    AlternativeResponseDTO toDTO(Alternative alternative);
}
