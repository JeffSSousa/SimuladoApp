package com.jeffssousa.SimuladoApp.usecase.question;

import com.jeffssousa.SimuladoApp.dto.CreateQuestionDTO;
import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Question;

import java.util.List;

public interface CreateQuestionUseCase {

    Question createQuestionForExam(CreateQuestionDTO dto);
}
