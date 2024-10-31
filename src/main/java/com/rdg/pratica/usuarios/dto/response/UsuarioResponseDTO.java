package com.rdg.pratica.usuarios.dto.response;

public record UsuarioResponseDTO(String id,
                                 String nome,
                                 String email) {

    @Override
    public String toString() {
        return "UsuarioResponseDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
