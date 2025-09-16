package com.projeto_inclus.sistema_de_fotos.rest.dto.response.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta padrão para erros da API")
public record UsuarioErrorResponse409DTO(
        @Schema(description = "Código HTTP do erro", example = "409")
        int status,

        @Schema(description = "Mensagem descritiva do erro", example = "Usuario Já Cadastrado")
        String message,

        @Schema(description = "Data e hora do erro", example = "2025-09-13T16:29:54")
        LocalDateTime timestamp,

        @Schema(description = "Detalhes adicionais", example = "Email já cadastrado")
        String details
) {}
