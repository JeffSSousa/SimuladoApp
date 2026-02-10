package com.jeffssousa.SimuladoApp.repository;

import com.jeffssousa.SimuladoApp.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
}
