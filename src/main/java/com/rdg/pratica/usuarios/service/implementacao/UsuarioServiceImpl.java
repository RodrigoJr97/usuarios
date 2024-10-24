package com.rdg.pratica.usuarios.service.implementacao;

import com.rdg.pratica.usuarios.dto.mapper.UsuarioMapper;
import com.rdg.pratica.usuarios.dto.request.UsuarioRequestDTO;
import com.rdg.pratica.usuarios.dto.response.UsuarioResponseDTO;
import com.rdg.pratica.usuarios.exceptions.BusinessException;
import com.rdg.pratica.usuarios.model.UsuarioEntity;
import com.rdg.pratica.usuarios.repository.UsuarioRepository;
import com.rdg.pratica.usuarios.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;
    private UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.repository = usuarioRepository;
        this.mapper = usuarioMapper;
    }

    @Override
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequest) {
        UsuarioEntity usuario = mapper.paraUsuarioEntity(usuarioRequest);

        repository.save(usuario);
        return mapper.paraUsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(String id) {
        UsuarioEntity usuario = verificaExisteId(id);

        return mapper.paraUsuarioResponse(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<UsuarioResponseDTO> listaMapper = repository.findAll()
                .stream()
                .map((usuarioEntity -> mapper.paraUsuarioResponse(usuarioEntity)))
                .collect(Collectors.toList());

        return listaMapper;
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(String id, UsuarioRequestDTO usuarioRequest) {
        UsuarioEntity usuario = verificaExisteId(id);

        usuario.setNome(usuarioRequest.nome());
        usuario.setEmail(usuarioRequest.email());

        repository.save(usuario);
        return mapper.paraUsuarioResponse(usuario);
    }

    private UsuarioEntity verificaExisteId(String id) {
        try {
            UsuarioEntity usuario = repository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("ID - " + id + " não encontrado"));

            return usuario;
        } catch (Exception e) {
            throw new BusinessException("Usuario não encontrado");
        }
    }

}
