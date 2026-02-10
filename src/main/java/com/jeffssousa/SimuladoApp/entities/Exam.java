package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_exam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
