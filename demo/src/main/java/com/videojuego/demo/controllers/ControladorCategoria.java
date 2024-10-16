package com.videojuego.demo.controllers;

import com.videojuego.demo.entities.Categoria;
import com.videojuego.demo.services.ServicioCategoria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ControladorCategoria {

    @Autowired
    private ServicioCategoria servicioCategoria;

    @GetMapping("/categorias")
    public String categorias(Model model) {

        try {
            List<Categoria> categorias = servicioCategoria.findAll();
            model.addAttribute("categorias", categorias);
            return "views/categorias/listarCategorias";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/categorias/formulario/{id}")
    public String formularioCategoria(
            Model model,
            @PathVariable("id") Long id) {
        try {
            Categoria categoria = null;
            // Creando categoria
            if (id == 0)
                categoria = new Categoria();

            // Categoria ya existente
            else
                categoria = servicioCategoria.findById(id);
            model.addAttribute("categoria", categoria);
            return "views/categorias/formularioCategoria";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/categorias/confirmarEliminar/{id}")
    public String confirmarEliminarCategoria(
            Model model,
            @PathVariable("id") Long id) {
        try {
            Categoria categoria = servicioCategoria.findById(id);
            model.addAttribute("categoria", categoria);
            return "views/categorias/confirmacionEliminar";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(
            Model model,
            @PathVariable("id") Long idCategoria) {
        try {
            servicioCategoria.deleteById(idCategoria);
            return "redirect:/categorias";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/guardarCategoria")
    public String guardarCategoria_(
            @Valid @ModelAttribute Categoria categoria,
            BindingResult result,
            Model model) {

        try {
            Long id = categoria.getId();
            if(result.hasErrors()){
                return "views/categorias/formularioCategoria";
            }
            if (id == null || id == 0) {
                servicioCategoria.saveOne(categoria);
            } else {
                servicioCategoria.updateOne(categoria, categoria.getId());
            }
            return "redirect:/categorias";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
