package com.jeffssousa.SimuladoApp.mapper;

import com.jeffssousa.SimuladoApp.dto.ExamRequestDTO;
import com.jeffssousa.SimuladoApp.entities.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    Exam toEntity (ExamRequestDTO dto);

}
