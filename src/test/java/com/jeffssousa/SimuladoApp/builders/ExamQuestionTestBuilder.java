package com.jeffssousa.SimuladoApp.builders;

import com.jeffssousa.SimuladoApp.entities.Exam;
import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;

public class ExamQuestionTestBuilder {

    private Long examQuestionId;

    private Exam exam = ExamTestBuilder.anExam().build();
    private Question question = QuestionTestBuilder.aQuestion().build();

    private ExamQuestionTestBuilder() {
    }

    public static ExamQuestionTestBuilder anExamQuestion() {
        return new ExamQuestionTestBuilder();
    }

    public ExamQuestionTestBuilder withExamQuestionId(Long examQuestionId) {
        this.examQuestionId = examQuestionId;
        return this;
    }

    public ExamQuestionTestBuilder withExam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public ExamQuestionTestBuilder withQuestion(Question question) {
        this.question = question;
        return this;
    }

    public ExamQuestion build() {
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExamQuestionId(examQuestionId);
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        return examQuestion;
    }
}
