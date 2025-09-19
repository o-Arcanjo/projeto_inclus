package com.projeto_inclus.sistema_de_fotos.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequestCreateAtualizarSenhaUsuario(
        @Schema(description = "Nova Senha de Usuário", example = "Ma123456789@.")
        @NotBlank(message = "Nova Senha é obrigatória")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$",
                message = "Senha deve conter pelo menos 1 número, 1 letra maiúscula, 1 minúscula e 1 caractere especial"
        )
        String senha
) {
}
