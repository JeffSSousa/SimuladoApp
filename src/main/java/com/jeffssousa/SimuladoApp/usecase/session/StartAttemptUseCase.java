package com.jeffssousa.SimuladoApp.usecase.session;

import com.jeffssousa.SimuladoApp.entities.ExamResult;

public interface StartAttemptUseCase {

    ExamResult startAttempt (Long examId);

}
