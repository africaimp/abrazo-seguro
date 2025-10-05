package com.abrazoseguro.registro.controlador;

import com.abrazoseguro.registro.dto.RegistroDTO;
import com.abrazoseguro.registro.dto.ServicioDTO;
import com.abrazoseguro.registro.servicio.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Description;

import java.util.List;

@RestController
public class RegistroController extends ControladorBase {

    @Autowired
    Registro registro;

    @RequestMapping(value = "/registro/servicios", method = RequestMethod.GET)
    @Description("Regresa el catálogo de servicios disponible")
    public @ResponseBody List<ServicioDTO> obtenerServicios() {
        return registro.obtenerServicios();
    }

    @RequestMapping(value = "/registro/contacto", method = RequestMethod.POST)
    @Description("Crea un registro de contacto")
    public @ResponseBody
    ResponseEntity crearRegistro(@RequestBody RegistroDTO registroDTO) {
        registro.crearRegistro(registroDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/registro/paciente/{pacienteId}", method = RequestMethod.GET)
    @Description("Obtiene el registro de un paciente")
    public @ResponseBody
    RegistroDTO obtenerRegistroPorPaciente(@PathVariable Long pacienteId) {
        return registro.obtenerRegistro(pacienteId);
    }

    @RequestMapping(value = "/registro/pacientes", method = RequestMethod.GET)
    @Description("Obtiene todos los registro de pacienter")
    public @ResponseBody
    List<RegistroDTO> obtenerRegistroPacientes() {
        return registro.obtenerRegistroPacientes();
    }
}
