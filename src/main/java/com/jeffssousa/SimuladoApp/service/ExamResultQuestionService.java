package com.jeffssousa.SimuladoApp.service;

import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.entities.ExamResultQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.repository.ExamQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultQuestionRepository;
import com.jeffssousa.SimuladoApp.repository.ExamResultRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExamResultQuestionService {


    private final ExamQuestionRepository examQuestionRepository;

    private final ExamResultRepository examResultRepository;

    private final ExamResultQuestionRepository examResultQuestionRepository;

    public List<ExamResultQuestion> createAll(UUID examResultId, Long examId) {

        ExamResult examResult = examResultRepository.findById(examResultId)
                                                    .orElseThrow(() -> new EntityNotFoundException("Tentativa não encontrada!"));

        List<Question> questions = examQuestionRepository.findQuestionsByExamId(examId);


       List<ExamResultQuestion> examResultQuestions = generateExamResultQuestions(questions, examResult);

        return examResultQuestionRepository.saveAll(examResultQuestions);

    }

    private List<ExamResultQuestion> generateExamResultQuestions(List<Question> questions, ExamResult examResult) {
        List<ExamResultQuestion> examResultQuestions = new ArrayList<>();

        int sequence = 0;

        List<Question> shuffledQuestions = new ArrayList<>(questions);
        Collections.shuffle(shuffledQuestions);

        for(Question question: shuffledQuestions){

            sequence++;

            ExamResultQuestion examResultQuestion = new ExamResultQuestion();
            examResultQuestion.setExamResult(examResult);
            examResultQuestion.setQuestion(question);
            examResultQuestion.setSequence(sequence);


            examResultQuestions.add(examResultQuestion);

        }

        return examResultQuestions;

    }

}
