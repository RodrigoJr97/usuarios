package com.rdg.pratica.usuarios.service;

import com.rdg.pratica.usuarios.dto.request.UsuarioRequestDTO;
import com.rdg.pratica.usuarios.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequest);

    UsuarioResponseDTO buscarUsuarioPorId(String id);

    List<UsuarioResponseDTO> listarUsuarios();

    UsuarioResponseDTO atualizarUsuario(String id, UsuarioRequestDTO usuarioRequest);

}
