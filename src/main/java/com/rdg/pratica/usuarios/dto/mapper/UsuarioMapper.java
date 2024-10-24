package com.rdg.pratica.usuarios.dto.mapper;

import com.rdg.pratica.usuarios.dto.request.UsuarioRequestDTO;
import com.rdg.pratica.usuarios.dto.response.UsuarioResponseDTO;
import com.rdg.pratica.usuarios.model.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "nome", source = "usuarioRequest.nome")
    @Mapping(target = "email", source = "usuarioRequest.email")
    UsuarioEntity paraUsuarioEntity(UsuarioRequestDTO usuarioRequest);

    @Mapping(target = "id", source = "usuarioEntity.id")
    @Mapping(target = "nome", source = "usuarioEntity.nome")
    @Mapping(target = "email", source = "usuarioEntity.email")
    UsuarioResponseDTO paraUsuarioResponse(UsuarioEntity usuarioEntity);

}
