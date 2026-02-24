package com.jeffssousa.SimuladoApp.usecase.session;


import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.service.ExamResultQuestionService;
import com.jeffssousa.SimuladoApp.service.ExamSessionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StartAttemptUseCaseImpl implements StartAttemptUseCase{


    private final ExamSessionService examSessionService;

    private final ExamResultQuestionService examResultQuestionService;

    @Override
    public ExamResult startAttempt(Long examId) {


        ExamResult examAttempt = examSessionService.start(examId);

        examResultQuestionService.createAll(
                                examAttempt.getExamResultId(),examId
                                );

        return examAttempt;
    }

}
