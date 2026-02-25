package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_alternative")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Alternative {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alternativeId;

    private String description;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "alternative")
    private List<UserAnswer> userAnswers;

}
