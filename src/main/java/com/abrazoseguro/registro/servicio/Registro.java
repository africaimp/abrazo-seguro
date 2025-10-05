package com.abrazoseguro.registro.servicio;

import com.abrazoseguro.registro.dao.DatosContactoDao;
import com.abrazoseguro.registro.dao.PacienteDao;
import com.abrazoseguro.registro.dao.ServicioDao;
import com.abrazoseguro.registro.dto.RegistroDTO;
import com.abrazoseguro.registro.dto.ServicioDTO;
import com.abrazoseguro.registro.model.DatosContacto;
import com.abrazoseguro.registro.model.Paciente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Registro {

    @Autowired
    ServicioDao servicioDao;

    @Autowired
    PacienteDao pacienteDao;

    @Autowired
    DatosContactoDao datosContactoDao;

    public List<ServicioDTO> obtenerServicios() {
        return servicioDao.findAll().stream().map(servicio ->
                            {
                                return new ServicioDTO(servicio.getId(), servicio.getNombre());
                            })
                .collect(Collectors.toList());
    }

    @Transactional
    public void crearRegistro(RegistroDTO registroDTO) {
        Paciente paciente = pacienteDao.save(crearPaciente(registroDTO));
        datosContactoDao.save(crearDatosContacto(registroDTO, paciente));
    }

    public List<RegistroDTO> obtenerRegistroPacientes() {
        return pacienteDao.findAll().stream().map(paciente -> {
            return entityToDTO(paciente, datosContactoDao.findById(paciente.getId()).get());
        }).collect(Collectors.toList());
    }

    public RegistroDTO obtenerRegistro(Long pacienteId) {
        Paciente paciente = pacienteDao.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado."));
        Optional<DatosContacto> datosContacto = datosContactoDao.findById(pacienteId);

        return entityToDTO(paciente, datosContacto.get());
    }

    private RegistroDTO entityToDTO(Paciente paciente, DatosContacto datosContacto) {
        RegistroDTO registroDTO = new RegistroDTO();
        registroDTO.setNombre(paciente.getNombre());
        registroDTO.setFechaNacimiento(paciente.getFechaNacimiento());
        registroDTO.setDiagnostico(paciente.getDiagnostico());
        registroDTO.setServicioId(paciente.getServicioId());

        registroDTO.setCuidad(datosContacto.getCiudad());
        registroDTO.setEmail(datosContacto.getEmail());
        registroDTO.setNombreTutor(datosContacto.getNombreTutor());
        registroDTO.setDireccion(datosContacto.getDireccion());
        registroDTO.setTelefono(datosContacto.getTelefono());

        return  registroDTO;
    }

    private DatosContacto crearDatosContacto(RegistroDTO registroDTO, Paciente paciente) {
        DatosContacto datosContacto = new DatosContacto();
        datosContacto.setPacienteId(paciente.getId());
        datosContacto.setCiudad(registroDTO.getCuidad());
        datosContacto.setDireccion(registroDTO.getDireccion());
        datosContacto.setEmail(registroDTO.getEmail());
        datosContacto.setTelefono(registroDTO.getTelefono());
        datosContacto.setNombreTutor(registroDTO.getNombreTutor());
        return datosContacto;
    }

    private Paciente crearPaciente(RegistroDTO registroDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(registroDTO.getNombre());
        paciente.setDiagnostico(registroDTO.getDiagnostico());
        paciente.setFechaNacimiento(registroDTO.getFechaNacimiento());
        paciente.setServicioId(registroDTO.getServicioId());
        return  paciente;
    }
}
