package com.jeffssousa.SimuladoApp.usecase.question;

import com.jeffssousa.SimuladoApp.dto.CreateQuestionDTO;
import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.mapper.QuestionAlternativeMapper;
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

    private final QuestionAlternativeMapper mapper;

    @Override
    public Question createQuestionForExam(CreateQuestionDTO dto) {


        Question question = mapper.toEntity(dto);
        List<Alternative> alternatives = dto.alternatives()
                .stream()
                .map(mapper::toEntity)
                .toList();

        question = questionService.save(question);
        Exam exam = examService.findById(dto.examId());

        ExamQuestion examQuestion = linkedExamQuestion(exam,question);
        examQuestionService.save(examQuestion);

        alternativeService.saveAll(alternatives, question.getQuestionId());

        return question;

    }

    private ExamQuestion linkedExamQuestion(Exam exam, Question question){
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        return examQuestion;
    }

}
