package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_examQuestion")
public class ExamQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examQuestionId;

    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
