package com.abrazoseguro.registro.controlador;

import com.abrazoseguro.registro.dto.ExcepcionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

public abstract class ControladorBase {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExcepcionDTO exceptionHandler(Exception e) {
        return new ExcepcionDTO("Hubo un error en el servicio.");
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExcepcionDTO notFoundExceptionHandler(Exception e) {
        return new ExcepcionDTO(e.getMessage());
    }
}
