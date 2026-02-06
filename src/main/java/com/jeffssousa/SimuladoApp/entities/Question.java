package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_question")
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionId;

    private String description;
    private String category;

    @OneToMany(mappedBy = "question")
    private List<ExamQuestion> examQuestions;

    @OneToMany(mappedBy = "question")
    private List<Alternative> alternativeList;

    @OneToMany(mappedBy = "question")
    private List<UserAnswer> userAnswers;

}
