package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tb_exam")
public class Exam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private String category;

    @OneToMany(mappedBy = "exam")
    private List<ExamResult> examResults;

    @OneToMany(mappedBy = "exam")
    private List<ExamQuestion> examQuestions;

}
