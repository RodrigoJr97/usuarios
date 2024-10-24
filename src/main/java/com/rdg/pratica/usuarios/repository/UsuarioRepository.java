package com.rdg.pratica.usuarios.repository;

import com.rdg.pratica.usuarios.model.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {
}
