package com.videojuego.demo.services;

import com.videojuego.demo.entities.Categoria;
import com.videojuego.demo.repositories.RepositorioCategoria;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Override;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCategoria implements ServicioBase<Categoria>{
    @Autowired
    private RepositorioCategoria repo;

    @Override
    @Transactional //rollback
    public List<Categoria> findAll() throws Exception {
        try {
            List<Categoria> entities = this.repo.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria findById(long id) throws Exception {
        try {
            Optional<Categoria> opt = this.repo.findById(id);
            return opt.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria saveOne(Categoria entity) throws Exception {
        try {
            Categoria categoria = this.repo.save(entity);
            return categoria;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Categoria updateOne(Categoria entity, long id) throws Exception {
        try {
            Optional<Categoria> opt = this.repo.findById(id);
            Categoria categoria = opt.get();
            categoria = this.repo.save(entity);
            return categoria;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Categoria> opt = this.repo.findById(id);
            if (opt.isPresent()) {
                Categoria categoria = opt.get();
                categoria.setActivo(false);
                this.repo.save(categoria);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
