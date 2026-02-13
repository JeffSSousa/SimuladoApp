package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.entities.ExamResultQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;

import java.util.UUID;

public class ExamResultQuestionTestBuilder {

    private UUID examResultQuestionId = UUID.randomUUID();
    private String status = "Pending";
    private Integer sequence = 1;
    private ExamResult examResult = ExamResultTestBuilder.anExamResult().build();
    private Question question = QuestionTestBuilder.aQuestion().build();

    private ExamResultQuestionTestBuilder() {
    }

    public static ExamResultQuestionTestBuilder anExamResultQuestion() {
        return new ExamResultQuestionTestBuilder();
    }

    public ExamResultQuestionTestBuilder withExamResultQuestionId(UUID id) {
        this.examResultQuestionId = id;
        return this;
    }

    public ExamResultQuestionTestBuilder withStatusPending() {
        this.status = "Pending";
        return this;
    }

    public ExamResultQuestionTestBuilder withStatusAnswered() {
        this.status = "Answered";
        return this;
    }

    public ExamResultQuestionTestBuilder withSequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public ExamResultQuestionTestBuilder withExamResult(ExamResult examResult) {
        this.examResult = examResult;
        return this;
    }

    public ExamResultQuestionTestBuilder withQuestion(Question question) {
        this.question = question;
        return this;
    }

    public ExamResultQuestion build() {
        ExamResultQuestion examResultQuestion = new ExamResultQuestion();
        examResultQuestion.setExamResultQuestionId(this.examResultQuestionId);
        examResultQuestion.setStatus(this.status);
        examResultQuestion.setSequence(this.sequence);
        examResultQuestion.setExamResult(this.examResult);
        examResultQuestion.setQuestion(this.question);
        return examResultQuestion;
    }
}
