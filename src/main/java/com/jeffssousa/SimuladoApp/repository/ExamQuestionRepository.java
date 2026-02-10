package com.jeffssousa.SimuladoApp.repository;

import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Long> {
}
