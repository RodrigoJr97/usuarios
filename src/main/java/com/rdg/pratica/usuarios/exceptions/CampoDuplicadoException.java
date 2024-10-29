package com.rdg.pratica.usuarios.exceptions;

public class CampoDuplicadoException extends RuntimeException{

    public CampoDuplicadoException() {
    }

    public CampoDuplicadoException(String message) {
        super(message);
    }
}
