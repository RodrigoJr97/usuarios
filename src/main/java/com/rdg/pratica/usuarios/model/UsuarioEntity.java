package com.rdg.pratica.usuarios.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usuario_entity")
public class UsuarioEntity {

    @Id
    private String id;
    private String nome;
    private String email;

}
