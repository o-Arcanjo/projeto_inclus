package com.projeto_inclus.sistema_de_fotos.rest.controller;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.error.ErrorResponse500DTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.error.UsuarioErrorResponse400DTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.error.UsuarioErrorResponse409DTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Usuários",
        description = "Operações CRUD para gestão de usuários do sistema")
public interface IUsuarioControllerApi {
    @Operation(
            summary = "Salva dados de Usuário",
            description = "Método para salvar dados de usuário",
            operationId = "cadastrarUsuario"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Usuário criado com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioResponseDTOCreate.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioErrorResponse400DTO.class)
            )
    )
    @ApiResponse(
            responseCode = "409",
            description = "Conflito - usuário já cadastrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioErrorResponse409DTO.class)
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro no servidor",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse500DTO.class)
            )
    )
    @PostMapping("/cadastrar")
    ResponseEntity<UsuarioResponseDTOCreate> cadastrarUsuario(
            @RequestBody @Valid UsuarioRequestDTO usuarioRequest
    );


    @Operation(
            summary = "Fazer login do Usuário",
            description = "Método para retornar token do usuário",
            operationId = "realizarLogin"
    )
    @PostMapping("/login")
    ResponseEntity<String> fazerLogin(
            @RequestBody @Valid UsuarioRequestCreateLogin usuarioRequest
    );

}
