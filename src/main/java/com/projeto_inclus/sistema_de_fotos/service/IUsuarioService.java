package com.projeto_inclus.sistema_de_fotos.service;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateAtualizarUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOAtualizar;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.domain.Identificador;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService extends IPageable<UsuarioResponseDTOCreate>{
    UsuarioResponseDTOCreate cadastrarUsuario(UsuarioRequestCreateUsuario request);
    UsuarioResponseDTOAtualizar atualizarUsuario(UUID id, UsuarioRequestCreateAtualizarUsuario request);
    String login(UsuarioRequestCreateLogin request);
    void deletarUsuario(UUID id);
    List<UsuarioResponseDTOCreate> listarTodosOsUsuarios();
    UsuarioResponseDTOCreate obterUsuarioPorId(UUID id);
    boolean verificarSeUsuarioCadastrado(Identificador identificador);
}
