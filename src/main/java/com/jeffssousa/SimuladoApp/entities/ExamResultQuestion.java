package com.jeffssousa.SimuladoApp.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_examResultQuestion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultQuestion {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID examResultQuestionId;

    private String status; // Answered | Pending

    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "exam_result_id")
    private ExamResult examResult;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public void withStatusPending(){
        this.status = "PENDING";
    }

}
