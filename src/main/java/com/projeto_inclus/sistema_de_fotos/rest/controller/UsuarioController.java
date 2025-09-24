package com.projeto_inclus.sistema_de_fotos.rest.controller;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateAtualizarUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOAtualizar;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements IUsuarioControllerApi{
    @Autowired
    private final IUsuarioService usuarioService;


    @Override
    public ResponseEntity<UsuarioResponseDTOCreate> cadastrarUsuario(
        @RequestBody @Valid UsuarioRequestCreateUsuario usuarioRequest) {
    UsuarioResponseDTOCreate response = usuarioService.cadastrarUsuario(usuarioRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> fazerLogin(
            @RequestBody @Valid UsuarioRequestCreateLogin usuarioRequest
    ) {
        String token = usuarioService.login((usuarioRequest));
        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<UsuarioResponseDTOAtualizar> atualizarUsuario(UUID id, UsuarioRequestCreateAtualizarUsuario usuarioRequest) {
        UsuarioResponseDTOAtualizar usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @Override
    public ResponseEntity<Page<UsuarioResponseDTOCreate>> findAll(@RequestParam(defaultValue = "0") int pagina,
                                                                  @RequestParam(defaultValue = "10") int itens) {
        Pageable pageable = PageRequest.of(pagina,itens);
        Page<UsuarioResponseDTOCreate> usuarios = usuarioService.listarEntidadePaginada(pageable);
    return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(UUID id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UsuarioResponseDTOCreate> obterUsuarioPorID(UUID id) {
        UsuarioResponseDTOCreate usuarioResponseDTOCreate = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok(usuarioResponseDTOCreate);
    }
}
