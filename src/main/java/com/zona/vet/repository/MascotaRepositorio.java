package com.zona.vet.repository;

import com.zona.vet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepositorio extends JpaRepository<Mascota, Integer> {
}
