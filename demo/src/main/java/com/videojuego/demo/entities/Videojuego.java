package com.videojuego.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @NotEmpty(message = "El titulo no puede estar vacio")
    private String titulo;

    @Size(min = 5, max = 1000, message = "La descripción debe tener entre 5 a 1000 caracteres")
    private String descripcion;

    @Lob //Large Object
    @Column(length = 1024)
    private String imagen;

    @Min(value = 5,message="El precio debe tener un minimo de 5")
    private float precio;

    @Min(value = 0,message="El stock debe tener un minimo de 0")
    private int stock;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message="La fecha no puede ser futura")
    private Date fechaLanzamiento;

    private boolean activo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    private Estudio estudio;

}
