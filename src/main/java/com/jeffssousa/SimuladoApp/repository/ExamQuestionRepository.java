package com.jeffssousa.SimuladoApp.repository;

import com.jeffssousa.SimuladoApp.entities.ExamQuestion;
import com.jeffssousa.SimuladoApp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Long> {

    @Query("""
            SELECT eq.question
            FROM ExamQuestion eq
            WHERE eq.exam.examId = :examId
            """)
    List<Question> findQuestionsByExamId(long examId);
}
