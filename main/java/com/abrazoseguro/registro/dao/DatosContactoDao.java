package com.abrazoseguro.registro.dao;

import com.abrazoseguro.registro.model.DatosContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosContactoDao extends JpaRepository<DatosContacto, Long> {
}
