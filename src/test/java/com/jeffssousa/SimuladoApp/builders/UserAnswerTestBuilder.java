package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.ExamResult;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.entities.UserAnswer;

import java.util.UUID;

public class UserAnswerTestBuilder {

    private UUID userAnswerId = UUID.randomUUID();
    private Question question;
    private ExamResult examResult;
    private Alternative alternative;

    public static UserAnswerTestBuilder builder() {
        return new UserAnswerTestBuilder();
    }

    public UserAnswerTestBuilder withUserAnswerId(UUID userAnswerId) {
        this.userAnswerId = userAnswerId;
        return this;
    }

    public UserAnswerTestBuilder withQuestion(Question question) {
        this.question = question;
        return this;
    }

    public UserAnswerTestBuilder withExamResult(ExamResult examResult) {
        this.examResult = examResult;
        return this;
    }

    public UserAnswerTestBuilder withAlternative(Alternative alternative) {
        this.alternative = alternative;
        return this;
    }

    public UserAnswer build() {
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserAnswerId(this.userAnswerId);
        userAnswer.setQuestion(this.question);
        userAnswer.setExamResult(this.examResult);
        userAnswer.setAlternative(this.alternative);
        return userAnswer;
    }
}
