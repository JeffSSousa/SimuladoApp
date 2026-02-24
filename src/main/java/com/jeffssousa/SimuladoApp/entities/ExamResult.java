package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_examResult")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "examResult")
    private List<ExamResultQuestion> examResultQuestions;

    public void start(int totalQuestions) {
        this.status = "IN_PROGRESS";
        this.initialTimestamp = Instant.now();
        this.currentQuestion = 1;
        this.totalQuestion = totalQuestions;
        this.correctAnswers = 0;
        this.finishedTimestamp = null;
    }
}
