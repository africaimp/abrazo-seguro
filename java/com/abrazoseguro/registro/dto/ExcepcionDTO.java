package com.abrazoseguro.registro.dto;

public class ExcepcionDTO {

    private String mensaje;

    public ExcepcionDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
