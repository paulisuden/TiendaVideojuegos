package com.videojuego.demo.services;

import com.videojuego.demo.entities.Estudio;
import com.videojuego.demo.repositories.RepositorioEstudio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Override;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioEstudio implements ServicioBase<Estudio> {
    @Autowired
    private RepositorioEstudio repo;

    @Override
    @Transactional //rollback
    public List<Estudio> findAll() throws Exception {
        try {
            List<Estudio> entities = this.repo.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio findById(long id) throws Exception {
        try {
            Optional<Estudio> opt = this.repo.findById(id);
            return opt.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio saveOne(Estudio entity) throws Exception {
        try {
            Estudio estudio = this.repo.save(entity);
            return estudio;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudio updateOne(Estudio entity, long id) throws Exception {
        try {
            Optional<Estudio> opt = this.repo.findById(id);
            Estudio estudio = opt.get();
            estudio = this.repo.save(entity);
            return estudio;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        try {
            Optional<Estudio> opt = this.repo.findById(id);
            if (opt.isPresent()) {
                Estudio estudio = opt.get();
                estudio.setActivo(false);
                this.repo.save(estudio);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
