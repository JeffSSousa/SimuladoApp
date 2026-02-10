package com.jeffssousa.SimuladoApp.usecase.question;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.service.AlternativeService;
import com.jeffssousa.SimuladoApp.service.ExamQuestionService;
import com.jeffssousa.SimuladoApp.service.ExamService;
import com.jeffssousa.SimuladoApp.service.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateQuestionUseCaseImpl implements CreateQuestionUseCase{


    private final QuestionService questionService;

    private final ExamQuestionService examQuestionService;

    private final AlternativeService alternativeService;

    private final ExamService examService;

    @Override
    public Question createQuestionForExam(Question question, Long examId, List<Alternative> alternatives) {


        question = questionService.save(question);
        Exam exam = examService.findById(examId);

        ExamQuestion examQuestion = linkedExamQuestion(exam,question);
        examQuestionService.save(examQuestion);


        linkedAlternativesToQuestion(alternatives,question);
        alternativeService.saveAll(alternatives, question.getQuestionId());

        return question;

    }


    private void linkedAlternativesToQuestion(List<Alternative> list, Question question){
        list.forEach(a -> a.setQuestion(question));
    }

    private ExamQuestion linkedExamQuestion(Exam exam, Question question){
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        return examQuestion;
    }

}
