package com.videojuego.demo.repositories;

import com.videojuego.demo.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {
    @Query(value = "select * from categorias where categorias.activo = true", nativeQuery = true)
    List<Categoria> findAllByActivo();

    @Query(value = "select * from categorias where categorias.id = :id and categorias.activo = true", nativeQuery = true)
    Optional<Categoria> findByIdAndActivo(@Param("id") long id);
}
