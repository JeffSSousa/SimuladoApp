package com.jeffssousa.SimuladoApp.mapper;

import com.jeffssousa.SimuladoApp.dto.AlternativeRequestDTO;
import com.jeffssousa.SimuladoApp.dto.CreateQuestionDTO;
import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Question;
import jakarta.persistence.Entity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionAlternativeMapper {

    Question toEntity (CreateQuestionDTO dto);

    Alternative toEntity(AlternativeRequestDTO dto);

}
