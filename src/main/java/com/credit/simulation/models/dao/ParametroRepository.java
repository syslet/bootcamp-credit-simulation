package com.credit.simulation.models.dao;

import com.credit.simulation.models.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    @Query("SELECT p FROM Parametro p WHERE LOWER(p.id) = LOWER(:id)")
    public List<Parametro> findById(@Param("id") String id);

    @Query("SELECT p FROM Parametro p")
    public List<Parametro> findByAll();

    @Query("SELECT p FROM Parametro p WHERE LOWER(p.familia) = LOWER(:family)")
    public List<Parametro> findByFamilyName(@Param("family") String family);

    @Query("SELECT p FROM Parametro p WHERE LOWER(p.producto) = LOWER(:product)")
    public List<Parametro> findByProductName(@Param("product") String product);

    @Query("SELECT p FROM Parametro p WHERE LOWER(p.familia) = LOWER(:family)")
    public List<Parametro> find(@Param("family") String family);

}
