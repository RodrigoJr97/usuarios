package com.rdg.pratica.usuarios.service.implementacao;

import com.rdg.pratica.usuarios.dto.mapper.UsuarioMapper;
import com.rdg.pratica.usuarios.dto.request.UsuarioRequestDTO;
import com.rdg.pratica.usuarios.dto.response.UsuarioResponseDTO;
import com.rdg.pratica.usuarios.exceptions.CampoDuplicadoException;
import com.rdg.pratica.usuarios.exceptions.CaracteresNaoPermitidosException;
import com.rdg.pratica.usuarios.exceptions.ElementoNaoEncontradoException;
import com.rdg.pratica.usuarios.model.UsuarioEntity;
import com.rdg.pratica.usuarios.repository.UsuarioRepository;
import com.rdg.pratica.usuarios.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
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
        verificaCaracteresDoNome(usuarioRequest.nome());
        verificaCampoDuplicado(usuarioRequest.email());

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

        verificaCaracteresDoNome(usuarioRequest.nome());
        verificaCampoDuplicado(usuarioRequest.email());

        usuario.setNome(usuarioRequest.nome());
        usuario.setEmail(usuarioRequest.email());

        repository.save(usuario);
        return mapper.paraUsuarioResponse(usuario);
    }

    @Override
    public void deletarUsuario(String id) {
        verificaExisteId(id);

        repository.deleteById(id);
    }

    // Metodos utilitarios
    private UsuarioEntity verificaExisteId(String id) {
        UsuarioEntity usuario = repository.findById(id)
                .orElseThrow(() -> new ElementoNaoEncontradoException("Usuario com id informado não encontrado"));

        return usuario;
    }

    private void verificaCampoDuplicado(String email) {
        List<UsuarioEntity> emails = repository.findAll()
                .stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());

        if (!emails.isEmpty()) {
            throw new CampoDuplicadoException("Email já está cadastrado");
        }
    }

    private void verificaCaracteresDoNome(String nome) {
        String regex = "^[A-Za-zÀ-ÿ\\s]+$";
        boolean valido = Pattern.matches(regex, nome);

        if (!valido) {
            throw new CaracteresNaoPermitidosException("Nome deve conter apenas letras e acentos");
        }
    }

}
