package com.projeto_inclus.sistema_de_fotos.rest.controller;
import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateAtualizarUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOAtualizar;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.error.ErrorResponse500DTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.error.UsuarioErrorResponse400DTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.error.UsuarioErrorResponse409DTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


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
            @RequestBody @Valid UsuarioRequestCreateUsuario usuarioRequest
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

    @Operation(
            summary = "Atualizar Usuário",
            description = "Método para retornar usuário atualizado",
            operationId = "atualizarUsuario"
    )
    @PutMapping("{id}")
    ResponseEntity<UsuarioResponseDTOAtualizar> atualizarUsuario(
            @Parameter(
                    description = "ID do usuário que será atualizado",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID uuid,

            @Parameter(
                    description = "Dados para atualizar o usuário",
                    required = true
            )
            @RequestBody @Valid UsuarioRequestCreateAtualizarUsuario usuarioRequest
            );

    @Operation(
           summary = "Retornar lista com determinada quantidade da entidade de usuário",
           description = "Método para retornar uma lista com usuários",
           operationId = "retornarListaPaginada"
    )
    @GetMapping("/usuariosPaginados")
    ResponseEntity<Page<UsuarioResponseDTOCreate>> findAll(@RequestParam int pagina, @RequestParam int itens);

    @Operation(
            summary = "Deletar usuário",
            description = "Método para excluir um usuário pelo seu ID",
            operationId = "deletarUsuario"
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarUsuario(
            @Parameter (
                    description = "ID do usuário que será deletado",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id
    );

    @Operation(
            summary = "Obter único usuário",
            description = "Métódo para obter um único usuário pelo seu ID",
            operationId = "obterUnicoUsuario"
    )
    @GetMapping("/{id}")
    ResponseEntity<UsuarioResponseDTOCreate> obterUsuarioPorID(
            @Parameter(
                    description = "ID do usuário que será encontrado",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000"
            )
            @PathVariable UUID id
    );

}
