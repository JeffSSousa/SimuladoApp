package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.entities.UserAnswer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamResultTestBuilder {

    private UUID examResultId = UUID.randomUUID();
    private String status = "IN_PROGRESS";
    private Instant initialTimestamp = Instant.now();
    private Instant finishedTimestamp = null;
    private Integer currentQuestion = 1;
    private Integer totalQuestion = 10;
    private Integer correctAnswers = 0;
    private Exam exam = null;
    private List<UserAnswer> userAnswers = new ArrayList<>();

    private ExamResultTestBuilder() {}

    public static ExamResultTestBuilder anExamResult() {
        return new ExamResultTestBuilder();
    }

    public ExamResultTestBuilder withExamResultId(UUID examResultId) {
        this.examResultId = examResultId;
        return this;
    }

    public ExamResultTestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ExamResultTestBuilder withInitialTimestamp(Instant initialTimestamp) {
        this.initialTimestamp = initialTimestamp;
        return this;
    }

    public ExamResultTestBuilder withFinishedTimestamp(Instant finishedTimestamp) {
        this.finishedTimestamp = finishedTimestamp;
        return this;
    }

    public ExamResultTestBuilder withCurrentQuestion(Integer currentQuestion) {
        this.currentQuestion = currentQuestion;
        return this;
    }

    public ExamResultTestBuilder withTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
        return this;
    }

    public ExamResultTestBuilder withCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
        return this;
    }

    public ExamResultTestBuilder withExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public ExamResultTestBuilder withUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
        return this;
    }

    public ExamResult build() {
        ExamResult examResult = new ExamResult();

        examResult.setExamResultId(examResultId);
        examResult.setStatus(status);
        examResult.setInitialTimestamp(initialTimestamp);
        examResult.setFinishedTimestamp(finishedTimestamp);
        examResult.setCurrentQuestion(currentQuestion);
        examResult.setTotalQuestion(totalQuestion);
        examResult.setCorrectAnswers(correctAnswers);
        examResult.setExam(exam);
        examResult.setUserAnswers(userAnswers);

        return examResult;
    }
}
