package com.projeto_inclus.sistema_de_fotos.rest.dto.request;

import com.projeto_inclus.sistema_de_fotos.rest.dto.IDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

/**
 * DTO usado para criar/atualizar o usuário.
 * Contém somente os dados que o usuário pode enviar
 *
 * */


@Builder
public record UsuarioRequestDTO (
    @Schema(description = "Nome Completo do usuário", example = "João Silva")
    @NotBlank(message="Nome é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    String nome,

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
    String senha,

    @Schema(description = "Data de nascimento do usuário", example = "2005-03-19")
    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve estar no passado")
    LocalDate dataNascimento
) implements IDTO {}



