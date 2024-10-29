package com.rdg.pratica.usuarios.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public record UsuarioRequestDTO(@NotEmpty
                                String nome,
                                @NotEmpty
                                @Email
                                String email) {
}
