package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_userAnswer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userAnswerId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "exam_result_id")
    private ExamResult examResult;

    @ManyToOne
    @JoinColumn(name = "alternative_id")
    private Alternative alternative;

}
