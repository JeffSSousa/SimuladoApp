package com.jeffssousa.SimuladoApp.usecase.question;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Question;

import java.util.List;

public interface CreateQuestionUseCase {

    Question createQuestionForExam(Question question, Long examId,List<Alternative> alternatives);
}
