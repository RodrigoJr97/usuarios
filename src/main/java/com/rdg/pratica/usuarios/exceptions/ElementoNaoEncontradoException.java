package com.rdg.pratica.usuarios.exceptions;

public class ElementoNaoEncontradoException extends RuntimeException {

    public ElementoNaoEncontradoException(String message) {
        super(message);
    }

    public ElementoNaoEncontradoException() {
        super();
    }

}
