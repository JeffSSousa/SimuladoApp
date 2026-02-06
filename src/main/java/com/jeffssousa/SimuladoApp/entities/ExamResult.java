package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_examResult")
public class ExamResult {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID examResultId;

    private String status;

    private Instant initialTimestamp;
    private Instant finishedTimestamp;

    private Integer currentQuestion;
    private Integer totalQuestion;

    private Integer correctAnswers;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany(mappedBy = "examResult")
    private List<UserAnswer> userAnswers;
}
