package com.abrazoseguro.registro.dao;

import com.abrazoseguro.registro.model.DatosContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosContactoDao extends JpaRepository<DatosContacto, Long> {
}
