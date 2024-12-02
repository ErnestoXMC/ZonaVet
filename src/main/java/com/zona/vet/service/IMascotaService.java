package com.zona.vet.service;


import com.zona.vet.model.Mascota;

import java.util.List;

public interface IMascotaService {
    public List<Mascota> findAll();
    public Mascota findById(Integer id);
    public void save(Mascota mascota);
    public void delete(Mascota mascota);
    public void deleteById(Integer id);
}
