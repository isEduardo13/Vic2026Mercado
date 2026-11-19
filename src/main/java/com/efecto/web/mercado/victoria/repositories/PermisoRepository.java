package com.efecto.web.mercado.victoria.repositories;

import com.efecto.web.mercado.victoria.entities.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    Optional<Permiso> findByNombre(String nombre);
}
