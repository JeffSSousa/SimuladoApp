package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "question")
    private List<ExamResultQuestion> examResultQuestions;

}
