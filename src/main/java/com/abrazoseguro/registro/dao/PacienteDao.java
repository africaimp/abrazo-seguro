package com.abrazoseguro.registro.dao;

import com.abrazoseguro.registro.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteDao extends JpaRepository<Paciente, Long> {
}
