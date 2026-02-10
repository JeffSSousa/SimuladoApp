package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.ExamResult;

import java.util.ArrayList;
import java.util.List;

public class ExamTestBuilder {

    private Long examId;
    private String category = "Tecnologia";
    private List<ExamResult> examResults = new ArrayList<>();
    private List<ExamQuestion> examQuestions = new ArrayList<>();

    private ExamTestBuilder() {}

    public static ExamTestBuilder anExam() {
        return new ExamTestBuilder();
    }

    public ExamTestBuilder withExamId(Long examId) {
        this.examId = examId;
        return this;
    }

    public ExamTestBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public ExamTestBuilder withExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
        return this;
    }

    public ExamTestBuilder withExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
        return this;
    }

    public Exam build() {
        Exam exam = new Exam();
        exam.setExamId(examId);
        exam.setCategory(category);
        exam.setExamResults(examResults);
        exam.setExamQuestions(examQuestions);
        return exam;
    }
}
