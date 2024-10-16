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

@Controller
public class ControladorVideojuego {
    @Autowired
    private ServicioVideojuego svcVideojuego;
    @Autowired
    private ServicioEstudio svcEstudio;
    @Autowired
    private ServicioCategoria svcCategoria;

    @GetMapping(value = "/videojuegos")
    public String crudVideojuego(Model model) throws Exception {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAll();
            model.addAttribute("videojuegos", videojuegos);
            return "views/videojuegos/listarVideojuegos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            model.addAttribute("categorias", svcCategoria.findAll());
            model.addAttribute("estudios", svcEstudio.findAll());
            System.out.println("id formulario," + id);
            if (id == 0) {
                System.out.println("if");
                model.addAttribute("videojuego", new Videojuego());
            } else {
                System.out.println("else");
                Videojuego videojuego = svcVideojuego.findByIdAndActivo(id);
                System.out.println(videojuego.getId());
                model.addAttribute("videojuego", videojuego);
            }
            return "views/videojuegos/formularioVideojuego";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/confirmacionEliminar/videojuego/{id}")
    public String confirmarEliminarVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            Videojuego videojuego = svcVideojuego.findByIdAndActivo(id);
            model.addAttribute("videojuego", videojuego);
            return "views/videojuegos/confirmacionEliminar";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            System.out.println("llego eliminar");
            svcVideojuego.deleteById(id);
            return "redirect:/videojuegos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/guardarVideojuego")
    public String guardarVideojuego_(Videojuego videojuego, Model model) {
        try {
            Long id = videojuego.getId();
            if (id == null || id == 0) {
                videojuego.setFechaLanzamiento(new Date());
                svcVideojuego.saveOne(videojuego);
            } else {
                svcVideojuego.updateOne(videojuego, videojuego.getId());
            }
            return "redirect:/videojuegos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
