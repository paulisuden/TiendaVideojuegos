package com.videojuego.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.videojuego.demo.entities.Estudio;
import com.videojuego.demo.entities.Estudio;
import com.videojuego.demo.services.ServicioEstudio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorEstudio {
    @Autowired
    private ServicioEstudio servicioEstudio;

    @GetMapping("/estudios")
    public String estudios(Model model) {
        try {
            List<Estudio> estudios = servicioEstudio.findAll();
            model.addAttribute("estudios", estudios);
            return "views/estudios/listarEstudios";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/estudios/formulario/{id}")
    public String formularioCategoria(
            Model model,
            @PathVariable("id") Long id) {
        try {
            Estudio estudio = null;
            // Creando estudio
            if (id == 0)
                estudio = new Estudio();

            // Estudio ya existente
            else
                estudio = servicioEstudio.findById(id);
            model.addAttribute("estudio", estudio);
            return "views/estudios/formularioEstudio";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/estudios/confirmarEliminar/{id}")
    public String confirmarEliminarCategoria(
            Model model,
            @PathVariable("id") Long id) {
        try {
            Estudio estudio = servicioEstudio.findById(id);
            model.addAttribute("estudio", estudio);
            return "views/estudios/confirmacionEliminar";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/estudios/eliminar/{id}")
    public String eliminarCategoria(
            Model model,
            @PathVariable("id") Long idCategoria) {
        try {
            servicioEstudio.deleteById(idCategoria);
            return "redirect:/estudios";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/guardarEstudio")
    public String guardarEstudio_(
            Estudio estudio,
            Model model) {

        try {
            Long id = estudio.getId();
            if (id == null || id == 0) {
                servicioEstudio.saveOne(estudio);
            } else {
                servicioEstudio.updateOne(estudio, estudio.getId());
            }
            return "redirect:/estudios";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
