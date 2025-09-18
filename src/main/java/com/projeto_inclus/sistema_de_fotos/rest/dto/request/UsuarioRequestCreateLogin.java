package com.projeto_inclus.sistema_de_fotos.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequestCreateLogin(
        @Schema(description = "Email do usuário", example = "joaosilva@gmail.com")
        @NotBlank(message="Email é obrigatório")
        @Email(message="Email deve ser válido")
        String email,

        @Schema(description = "Senha do usuário", example = "M@a1234.")
        @NotBlank
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$",
                message = "Senha deve conter pelo menos 1 número, 1 letra maiúscula, 1 minúscula e 1 caractere especial"
        )
        @Size(min = 8, max = 20, message="Senha deve ter entre 8 e 20 caracteres")
        String senha
) {
}
