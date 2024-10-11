package com.videojuego.demo.controllers;


import com.videojuego.demo.entities.Videojuego;
import com.videojuego.demo.services.ServicioCategoria;
import com.videojuego.demo.services.ServicioEstudio;
import com.videojuego.demo.services.ServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ControladorVideojuego {
    @Autowired
    private ServicioVideojuego svcVideojuego;
    @Autowired
    private ServicioEstudio svcEstudio;
    @Autowired
    private ServicioCategoria svcCategoria;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAllByActivo();
            model.addAttribute("videojuegos", videojuegos);
            return "views/inicio";
        }catch (Exception e){
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

    @GetMapping(value="/crud")
    public String crudVideojuego(Model model) throws Exception {
        try {
            List<Videojuego> videojuegos = this.svcVideojuego.findAll();
            model.addAttribute("videojuegos", videojuegos);
            return "views/crud";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value="/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            model.addAttribute("categorias", svcCategoria.findAll());
            model.addAttribute("estudios", svcEstudio.findAll());
            if (id == null || id == 0){
                model.addAttribute("videojuego", new Videojuego());
            }else{
                model.addAttribute("videojuego", svcVideojuego.findByIdAndActivo(id));
            }
            return "views/formulario/videojuego";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value="/confirmacionEliminar/videojuego/{id}")
    public String confirmarEliminarVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            Videojuego videojuego = svcVideojuego.findByIdAndActivo(id);
            model.addAttribute("videojuego", videojuego);
            return "views/confirmacionEliminar/videojuego";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping(value="/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id") Long id) throws Exception {
        try {
            System.out.println("llego eliminar");
            svcVideojuego.deleteById(id);
            return "redirect:/crud";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @PostMapping("/guardarVideojuego")
    public String guardarVideojuego_( Videojuego videojuego, Model model) {
        try {
            System.out.println("try");
            Long id = videojuego.getId();
            System.out.println(id);
            if (id == null || id == 0){
                System.out.println("if null");
                videojuego.setFechaLanzamiento(new Date());
                svcVideojuego.saveOne(videojuego);
            }else{
                System.out.println("else");
                svcVideojuego.updateOne(videojuego, videojuego.getId());
            }
            return "redirect:/crud";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
