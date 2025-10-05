package com.abrazoseguro.registro.dao;

import com.abrazoseguro.registro.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioDao extends JpaRepository<Servicio, Long> {
}
