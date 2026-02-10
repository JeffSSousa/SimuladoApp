package com.jeffssousa.SimuladoApp.repository;

import com.jeffssousa.SimuladoApp.entities.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, UUID> {
}
