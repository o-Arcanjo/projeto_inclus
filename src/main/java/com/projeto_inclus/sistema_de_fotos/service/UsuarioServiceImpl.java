package com.projeto_inclus.sistema_de_fotos.service;

import com.projeto_inclus.sistema_de_fotos.domain.Identificador;
import com.projeto_inclus.sistema_de_fotos.domain.IdentificadorFactory;
import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import com.projeto_inclus.sistema_de_fotos.mapper.UsuarioMapper;
import com.projeto_inclus.sistema_de_fotos.repository.IUsuarioRepository;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.util.EstruturaTokenFactory;
import com.projeto_inclus.sistema_de_fotos.util.Payload;
import com.projeto_inclus.sistema_de_fotos.util.PayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{

    private final IAuthService authService;
    private final IUsuarioRepository usuarioRepository;
    private final UsuarioMapper<UsuarioResponseDTOCreate> usuarioCreateMapper;
    private final ITokenService tokenService;

    @Override
    public boolean verificarSeUsuarioCadastrado (Identificador identificador) {
        return identificador.existeNoRepositorio(usuarioRepository);
    }

    @Override
    public UsuarioResponseDTOCreate cadastrarUsuario(UsuarioRequestDTO request) {
       var instanciar = IdentificadorFactory.criarEmailIdentificador(request.email());
       if(verificarSeUsuarioCadastrado(instanciar)){
           return null;
       }
       Usuario usuario = usuarioCreateMapper.converterEmEntidade(request);
       String senhaCriptografada = authService.criptografarSenha(usuario.getSenha());
       usuario.setSenha(senhaCriptografada);
       Usuario usuarioSalvo = usuarioRepository.save(usuario);
       return usuarioCreateMapper.converterEmDTO(usuarioSalvo);
    }

    @Override
    public String login(UsuarioRequestDTO request){
        Usuario usuario = usuarioRepository.findUsuarioByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        authService.verificarSenha(request.senha(), usuario.getSenha());
        Payload payload = PayloadFactory.produzirPayload(usuario);
        return tokenService.gerarToken(EstruturaTokenFactory.produzirEstruturaParaToken(payload));
    }

    @Override
    public UsuarioResponseDTOCreate atualizarUsuario(UUID id, UsuarioRequestDTO request) {
        return null;
    }

    @Override
    public void deletarUsuario(UUID id) {

    }

    @Override
    public List<UsuarioResponseDTOCreate> listarTodosOsUsuarios() {
        return List.of();
    }


    @Override
    public UsuarioResponseDTOCreate obterUsuarioPorId(UUID id) {
        return null;
    }


    @Override
    public Page<UsuarioResponseDTOCreate> listarEntidadePaginada(Pageable pageable) {
        return null;
    }
}
