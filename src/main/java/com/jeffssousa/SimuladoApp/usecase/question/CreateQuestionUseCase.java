package com.jeffssousa.SimuladoApp.usecase.question;

import com.jeffssousa.SimuladoApp.dto.CreateQuestionDTO;
import com.jeffssousa.SimuladoApp.entities.Question;


public interface CreateQuestionUseCase {

    Question createQuestionForExam(CreateQuestionDTO dto);
}
