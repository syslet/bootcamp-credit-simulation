package com.credit.simulation.models.dao;

import com.credit.simulation.models.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @Query("SELECT p FROM Persona p")
    public List<Persona> findByAll();

    @Query("SELECT p FROM Persona p WHERE LOWER(p.dni) = LOWER(:dni)")
    public List<Persona> findByDNI(@Param("dni") String dni);
}
