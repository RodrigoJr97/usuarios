package com.rdg.pratica.usuarios.controller;

import com.rdg.pratica.usuarios.dto.request.UsuarioRequestDTO;
import com.rdg.pratica.usuarios.dto.response.UsuarioResponseDTO;
import com.rdg.pratica.usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO response = service.salvarUsuario(usuarioRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable String id) {
        UsuarioResponseDTO response = service.buscarUsuarioPorId(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> response = service.listarUsuarios();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable String id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO response = service.atualizarUsuario(id, usuarioRequestDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
