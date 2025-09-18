package com.projeto_inclus.sistema_de_fotos.service;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.domain.Identificador;

import java.util.List;
import java.util.UUID;

public interface IUsuarioService extends IPageable<UsuarioResponseDTOCreate>{
    UsuarioResponseDTOCreate cadastrarUsuario(UsuarioRequestDTO request);
    UsuarioResponseDTOCreate atualizarUsuario(UUID id, UsuarioRequestDTO request);
    String login(UsuarioRequestCreateLogin request);
    void deletarUsuario(UUID id);
    List<UsuarioResponseDTOCreate> listarTodosOsUsuarios();
    UsuarioResponseDTOCreate obterUsuarioPorId(UUID id);
    boolean verificarSeUsuarioCadastrado(Identificador identificador);
}
