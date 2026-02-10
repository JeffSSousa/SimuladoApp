package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import com.jeffssousa.SimuladoApp.entities.UserAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionTestBuilder {

    private UUID questionId;
    private String description = "Qual é a capital do Brasil?";
    private String category = "Geografia";

    private List<ExamQuestion> examQuestions = new ArrayList<>();
    private List<Alternative> alternativeList = new ArrayList<>();
    private List<UserAnswer> userAnswers = new ArrayList<>();

    private QuestionTestBuilder() {
    }

    public static QuestionTestBuilder aQuestion() {
        return new QuestionTestBuilder();
    }

    public QuestionTestBuilder withQuestionId(UUID questionId) {
        this.questionId = questionId;
        return this;
    }

    public QuestionTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public QuestionTestBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public QuestionTestBuilder withExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
        return this;
    }

    public QuestionTestBuilder withAlternatives(List<Alternative> alternatives) {
        this.alternativeList = alternatives;
        return this;
    }

    public QuestionTestBuilder withUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
        return this;
    }

    public Question build() {
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setDescription(description);
        question.setCategory(category);
        question.setExamQuestions(examQuestions);
        question.setAlternativeList(alternativeList);
        question.setUserAnswers(userAnswers);
        return question;
    }
}
