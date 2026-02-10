package com.jeffssousa.SimuladoApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_alternative")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Alternative {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID alternativeId;

    private String description;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "alternative")
    private List<UserAnswer> userAnswers;

}
