package com.videojuego.demo.repositories;

import com.videojuego.demo.entities.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioVideojuego extends JpaRepository<Videojuego, Long> {

    @Query(value = "select * from videojuegos where videojuegos.activo = true", nativeQuery = true)
    List<Videojuego> findAllByActivo();

    @Query(value = "select * from videojuegos where videojuegos.id = :id and videojuegos.activo = true", nativeQuery = true)
    Optional<Videojuego> findByIdAndActivo(@Param("id") long id);

    @Query(value = "select * from videojuegos where videojuegos.titulo = %:q% and videojuegos.activo = true", nativeQuery = true)
    List<Videojuego> findByTitle(@Param("q") String q);
}
