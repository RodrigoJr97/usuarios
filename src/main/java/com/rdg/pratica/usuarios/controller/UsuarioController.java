package com.rdg.pratica.usuarios.controller;

import com.rdg.pratica.usuarios.dto.request.UsuarioRequestDTO;
import com.rdg.pratica.usuarios.dto.response.UsuarioResponseDTO;
import com.rdg.pratica.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private static final Logger log_canal = LoggerFactory.getLogger(UsuarioController.class);

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO response = service.salvarUsuario(usuarioRequestDTO);

        log_canal.info("Status: " + HttpStatus.CREATED.value() + " - Request: " + usuarioRequestDTO + " Response: " + response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable String id) {
        UsuarioResponseDTO response = service.buscarUsuarioPorId(id);

        log_canal.info("Status: " + HttpStatus.OK.value() + " - Request: " + id + " Response: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> response = service.buscarUsuarios();

        log_canal.info("Status: " + HttpStatus.OK.value() + " - Request: "  + " Response: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable String id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO response = service.atualizarUsuario(id, usuarioRequestDTO);

        log_canal.info("Status: " + HttpStatus.OK.value() + " - Request: " + usuarioRequestDTO + " Response: " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable String id) {
        service.deletarUsuario(id);

        log_canal.info("Status: " + HttpStatus.OK.value() + " - Request: " + " Response: ");
        return new ResponseEntity("Usario deletado", HttpStatus.OK);
    }

}
