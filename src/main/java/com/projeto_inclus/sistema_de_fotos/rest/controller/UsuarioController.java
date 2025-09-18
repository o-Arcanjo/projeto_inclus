package com.projeto_inclus.sistema_de_fotos.rest.controller;

import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements IUsuarioControllerApi{
    @Autowired
    private final IUsuarioService usuarioService;

    @Override
    public ResponseEntity<UsuarioResponseDTOCreate> cadastrarUsuario(
        @RequestBody @Valid UsuarioRequestDTO usuarioRequest) {
    UsuarioResponseDTOCreate response = usuarioService.cadastrarUsuario(usuarioRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> fazerLogin(
            @RequestBody @Valid UsuarioRequestCreateLogin usuarioRequest
    ) {
        String response = usuarioService.login((usuarioRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
