package com.jeffssousa.SimuladoApp.repository;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import com.jeffssousa.SimuladoApp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, UUID> {

    List<Alternative> findAllByQuestion(Question question);
}
