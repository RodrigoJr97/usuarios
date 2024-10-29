package com.rdg.pratica.usuarios.exceptions;

public class CaracteresNaoPermitidosException extends RuntimeException{

    public CaracteresNaoPermitidosException() {
    }

    public CaracteresNaoPermitidosException(String message) {
        super(message);
    }
}
