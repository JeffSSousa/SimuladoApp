package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.entities.UserAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlternativeTestBuilder {

    private UUID alternativeId;
    private String description = "Alternativa A";
    private boolean isCorrect = false;

    private Question question = QuestionTestBuilder.aQuestion().build();
    private List<UserAnswer> userAnswers = new ArrayList<>();

    private AlternativeTestBuilder() {
    }

    public static AlternativeTestBuilder anAlternative() {
        return new AlternativeTestBuilder();
    }

    public AlternativeTestBuilder withAlternativeId(UUID alternativeId) {
        this.alternativeId = alternativeId;
        return this;
    }

    public AlternativeTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public AlternativeTestBuilder correct() {
        this.isCorrect = true;
        return this;
    }

    public AlternativeTestBuilder incorrect() {
        this.isCorrect = false;
        return this;
    }

    public AlternativeTestBuilder withQuestion(Question question) {
        this.question = question;
        return this;
    }

    public AlternativeTestBuilder withUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
        return this;
    }

    public Alternative build() {
        Alternative alternative = new Alternative();
        alternative.setAlternativeId(alternativeId);
        alternative.setDescription(description);
        alternative.setCorrect(isCorrect);
        alternative.setQuestion(question);
        alternative.setUserAnswers(userAnswers);
        return alternative;
    }
}
