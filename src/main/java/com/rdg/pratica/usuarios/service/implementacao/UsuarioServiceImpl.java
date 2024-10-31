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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final String ENTRADA = "Entrada da service = {} - {}";
    private static final String SAIDA = "Saida da service = {} - {}";

    private static final String SALVAR = "UsuarioServiceImpl - salvarUsuario";
    private static final String BUSCAR_POR_ID = "UsuarioServiceImpl - buscarUsuarioPorId";
    private static final String BUSCAR = "UsuarioServiceImpl - buscarUsuarios";
    private static final String ATUALIZAR = "UsuarioServiceImpl - atualizarUsuario";
    private static final String DELETAR = "UsuarioServiceImpl - deletarUsuario";

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private UsuarioRepository repository;
    private UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.repository = usuarioRepository;
        this.mapper = usuarioMapper;
    }

    @Override
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequest) {
        log.info(ENTRADA, SALVAR, usuarioRequest);
        verificaCaracteresDoNome(usuarioRequest.nome());
        verificaCampoDuplicado(usuarioRequest.email());

        UsuarioEntity usuario = mapper.paraUsuarioEntity(usuarioRequest);

        repository.save(usuario);
        log.info(SAIDA, SALVAR, usuario);
        return mapper.paraUsuarioResponse(usuario);
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(String id) {
        log.info(ENTRADA, BUSCAR_POR_ID, id);
        UsuarioEntity usuario = verificaExisteId(id);

        log.info(SAIDA, BUSCAR_POR_ID, usuario);
        return mapper.paraUsuarioResponse(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> buscarUsuarios() {
        log.info(ENTRADA, BUSCAR);
        List<UsuarioResponseDTO> listaMapper = repository.findAll()
                .stream()
                .map((usuarioEntity -> mapper.paraUsuarioResponse(usuarioEntity)))
                .collect(Collectors.toList());

        log.info(SAIDA, BUSCAR, listaMapper);
        return listaMapper;
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(String id, UsuarioRequestDTO usuarioRequest) {
        log.info(ENTRADA, ATUALIZAR, id);
        UsuarioEntity usuario = verificaExisteId(id);

        verificaCaracteresDoNome(usuarioRequest.nome());
        verificaCampoDuplicado(usuarioRequest.email());

        usuario.setNome(usuarioRequest.nome());
        usuario.setEmail(usuarioRequest.email());

        repository.save(usuario);

        log.info(SAIDA, ATUALIZAR, usuario);
        return mapper.paraUsuarioResponse(usuario);
    }

    @Override
    public void deletarUsuario(String id) {
        log.info(ENTRADA, DELETAR, id);
        verificaExisteId(id);

        log.info(SAIDA, DELETAR);
        repository.deleteById(id);
    }

    // Metodos utilitarios
    private UsuarioEntity verificaExisteId(String id) {
        log.info("INICIO - Verificação de ID");
        UsuarioEntity usuario = repository.findById(id)
                .orElseThrow(() -> new ElementoNaoEncontradoException("Usuario com id informado não encontrado"));

        log.info("FIM - Verificação de ID");
        return usuario;
    }

    private void verificaCampoDuplicado(String email) {
        log.info("INICIO - Verificação de campo duplicado");
        List<UsuarioEntity> emails = repository.findAll()
                .stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());

        if (!emails.isEmpty()) {
            throw new CampoDuplicadoException("Email já está cadastrado");
        }
        log.info("FIM - Verificação de campo duplicado");
    }

    private void verificaCaracteresDoNome(String nome) {
        log.info("INICIO - Verificação de caracteres do nome");
        String regex = "^[A-Za-zÀ-ÿ\\s]+$";
        boolean valido = Pattern.matches(regex, nome);

        if (!valido) {
            throw new CaracteresNaoPermitidosException("Nome deve conter apenas letras e acentos");
        }
        log.info("FIM - Verificação de caracteres do nome");
    }

}
