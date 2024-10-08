package com.videojuego.demo.repositories;

import com.videojuego.demo.entities.Estudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositorioEstudio extends JpaRepository<Estudio, Long> {
    @Query(value = "select * from estudios where estudios.activo = true", nativeQuery = true)
    List<Estudio> findAllByActivo();

    @Query(value = "select * from estudios where estudios.id = :id and estudios.activo = true", nativeQuery = true)
    Optional<Estudio> findByIdAndActivo(@Param("id") long id);
}
