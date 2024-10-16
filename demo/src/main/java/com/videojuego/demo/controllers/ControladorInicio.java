package com.videojuego.demo.controllers;

import com.videojuego.demo.entities.Videojuego;
import com.videojuego.demo.services.ServicioCategoria;
import com.videojuego.demo.services.ServicioEstudio;
import com.videojuego.demo.services.ServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorInicio {
    @Autowired
    private ServicioVideojuego svcVideojuego;

    @GetMapping("/")
    public String index() {
        return "redirect:/inicio";
    }

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAllByActivo();
            model.addAttribute("videojuegos", videojuegos);
            return "views/inicio";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") long id) {
        try {
            Videojuego videojuego = this.svcVideojuego.findByIdAndActivo(id);
            model.addAttribute("videojuego", videojuego);
            return "views/detalle";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("busqueda")
    public String buscarVideojuego(Model model, @RequestParam(value = "titulo", required = false) String titulo) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findByTitle(titulo);
            model.addAttribute("videojuegos", videojuegos);
            return "views/busqueda";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
