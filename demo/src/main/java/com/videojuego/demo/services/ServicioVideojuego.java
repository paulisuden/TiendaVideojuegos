package com.videojuego.demo.services;

import com.videojuego.demo.entities.Videojuego;
import com.videojuego.demo.repositories.RepositorioVideojuego;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Override;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioVideojuego implements ServicioBase<Videojuego>{
    @Autowired
    private RepositorioVideojuego repo;

    @Override
    @Transactional //rollback
    public List<Videojuego> findAll() throws Exception {
        try {
            List<Videojuego> entities = this.repo.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego findById(long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.repo.findById(id);
            return opt.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego saveOne(Videojuego entity) throws Exception {
        try {
            Videojuego videojuego = this.repo.save(entity);
            return videojuego;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Videojuego updateOne(Videojuego entity, long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.repo.findById(id);
            Videojuego videojuego = opt.get();
            videojuego = this.repo.save(entity);
            return videojuego;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Videojuego> opt = this.repo.findById(id);
            if (opt.isPresent()) {
                Videojuego videojuego = opt.get();
                videojuego.setActivo(false);
                this.repo.save(videojuego);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //hacen lo mismo que los primeros dos metodos
    @Transactional
    public List<Videojuego> findAllByActivo() throws Exception{
        try {
            List<Videojuego> entities = this.repo.findAllByActivo();
            return entities;
        } catch (Exception e) {
            throw new Exception((e.getMessage()));
        }
    }

    @Transactional
    public Videojuego findByIdAndActivo(long id) throws Exception{
        try {
            Optional<Videojuego> opt = this.repo.findByIdAndActivo(id);
            return opt.get();
        } catch (Exception e) {
            throw new Exception((e.getMessage()));
        }
    }

    @Transactional
    public List<Videojuego> findByTitle(String titulo) throws Exception {
        try{
            List<Videojuego> videojuegos = this.repo.findByTitle(titulo);
            return videojuegos;
        }catch (Exception e){
            throw new Exception((e.getMessage()));
        }
    }

}
