package com.projeto_inclus.sistema_de_fotos.service;
import com.projeto_inclus.sistema_de_fotos.domain.Identificador;
import com.projeto_inclus.sistema_de_fotos.domain.IdentificadorFactory;
import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import com.projeto_inclus.sistema_de_fotos.exception.Conflito;
import com.projeto_inclus.sistema_de_fotos.exception.NaoAutenticado;
import com.projeto_inclus.sistema_de_fotos.exception.RecursoNaoEncontrado;
import com.projeto_inclus.sistema_de_fotos.repository.IUsuarioRepository;
import com.projeto_inclus.sistema_de_fotos.rest.controller.MapperFactory;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateAtualizarUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOAtualizar;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.util.EstruturaTokenFactory;
import com.projeto_inclus.sistema_de_fotos.util.Payload;
import com.projeto_inclus.sistema_de_fotos.util.PayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService{

    private final IAuthService authService;
    private final IUsuarioRepository usuarioRepository;
    private final ITokenService tokenService;

    @Override
    public boolean verificarSeUsuarioCadastrado (Identificador identificador) {
        return identificador.existeNoRepositorio(usuarioRepository);
    }

    @Override
    public UsuarioResponseDTOCreate cadastrarUsuario(UsuarioRequestCreateUsuario request) {
       var instanciar = IdentificadorFactory.criarEmailIdentificador(request.email());
       if(verificarSeUsuarioCadastrado(instanciar)){
          throw new Conflito("Usuário já cadastrado");
       }
       Usuario usuario = MapperFactory.getMapper(UsuarioRequestCreateUsuario.class).converterEmEntidade(request);
       String senhaCriptografada = authService.criptografarSenha(usuario.getSenha());
       usuario.setSenha(senhaCriptografada);
       Usuario usuarioSalvo = usuarioRepository.save(usuario);
       return MapperFactory.getMapper(UsuarioResponseDTOCreate.class).converterEmDTO(usuarioSalvo);
    }

    @Override
    public String login(UsuarioRequestCreateLogin request){
        Usuario usuario = usuarioRepository.findUsuarioByEmail(request.email())
                .orElseThrow(() -> new NaoAutenticado("Erro de credenciais"));

        if(!authService.verificarSenha(request.senha(), usuario.getSenha())){
            throw new NaoAutenticado("Error de credenciais");
        }
        Payload payload = PayloadFactory.produzirPayload(usuario);
        return tokenService.gerarToken(EstruturaTokenFactory.produzirEstruturaParaToken(payload));
    }

    @Override
    public UsuarioResponseDTOAtualizar atualizarUsuario(UUID id, UsuarioRequestCreateAtualizarUsuario request) {
        Usuario usuarioCadastrado = usuarioRepository.findUsuarioById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
        Usuario usuarioRequest = MapperFactory.getMapper(UsuarioRequestCreateAtualizarUsuario.class).converterEmEntidade(request);
        usuarioCadastrado.setNome(usuarioRequest.getNome());
        usuarioCadastrado.setDataNascimento(usuarioRequest.getDataNascimento());
        Usuario usuario = usuarioRepository.save(usuarioCadastrado);
        return MapperFactory.getMapper(UsuarioResponseDTOAtualizar.class).converterEmDTO(usuario);
    }

    @Override
    public void deletarUsuario(UUID id) {
        Usuario usuarioCadastrado = usuarioRepository.findUsuarioById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuário não encontrado"));
        usuarioRepository.delete(usuarioCadastrado);
    }

    @Override
    public List<UsuarioResponseDTOCreate> listarTodosOsUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> MapperFactory.getMapper(UsuarioResponseDTOCreate.class)
                        .converterEmDTO(usuario))
                        .toList();
    }


    @Override
    public UsuarioResponseDTOCreate obterUsuarioPorId(UUID id) {
        Usuario usuarioCadastrado = usuarioRepository.findUsuarioById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Usuario nao encontrado"));
        return MapperFactory.getMapper(UsuarioResponseDTOCreate.class).converterEmDTO(usuarioCadastrado);
    }


    @Override
    public Page<UsuarioResponseDTOCreate> listarEntidadePaginada(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(usuario -> MapperFactory.getMapper(UsuarioResponseDTOCreate.class).converterEmDTO(usuario));
    }
}
