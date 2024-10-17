package com.videojuego.demo.controllers;

import com.videojuego.demo.entities.Videojuego;
import com.videojuego.demo.services.ServicioCategoria;
import com.videojuego.demo.services.ServicioEstudio;
import com.videojuego.demo.services.ServicioVideojuego;
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
            if (id == 0) {
                model.addAttribute("videojuego", new Videojuego());
            } else {
                Videojuego videojuego = svcVideojuego.findByIdAndActivo(id);
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
            svcVideojuego.deleteById(id);
            return "redirect:/videojuegos";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/guardarVideojuego")
    public String guardarVideojuego_(@Valid @ModelAttribute("videojuego") Videojuego videojuego, BindingResult result, Model model) {
        try {
            Long id = videojuego.getId();
            if(result.hasErrors()){
                model.addAttribute("categorias",this.svcCategoria.findAll());
                model.addAttribute("estudios",this.svcEstudio.findAll());
                return "views/videojuegos/formularioVideojuego";
            }

            if (id == null || id == 0) {
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
