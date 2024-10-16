package com.videojuego.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "videojuegos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;

    @Lob
    @Column(length = 1024)
    private String imagen;
    private float precio;
    private int stock;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaLanzamiento;
    private boolean activo = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    private Estudio estudio;

}
