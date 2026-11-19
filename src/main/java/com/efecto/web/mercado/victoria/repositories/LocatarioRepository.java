package com.efecto.web.mercado.victoria.repositories;

import com.efecto.web.mercado.victoria.entities.Locatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocatarioRepository extends JpaRepository<Locatario, Long> {

}
