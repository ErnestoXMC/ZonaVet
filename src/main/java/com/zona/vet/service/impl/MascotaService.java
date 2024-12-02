package com.zona.vet.service.impl;

import com.zona.vet.model.Mascota;
import com.zona.vet.repository.MascotaRepositorio;
import com.zona.vet.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    @Override
    public List<Mascota> findAll() {
        List<Mascota> mascotas = mascotaRepositorio.findAll();
        return mascotas;
    }

    @Override
    public Mascota findById(Integer id) {
        return mascotaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void save(Mascota mascota) {
        mascotaRepositorio.save(mascota);
    }

    @Override
    public void delete(Mascota mascota) {
        mascotaRepositorio.delete(mascota);
    }

    @Override
    public void deleteById(Integer id) {
        mascotaRepositorio.deleteById(id);
    }
}
