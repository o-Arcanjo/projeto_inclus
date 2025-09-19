package com.projeto_inclus.sistema_de_fotos.rest.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsuarioRequestCreateAtualizarUsuario(
    @Schema(description = "Nome do usuário", example = "Joao da Silva")
    @Nullable
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
     String nome,

    @Schema(description = "Data de Nascimento do usuário", example = "2005-03-18")
    @Nullable
    @Past(message = "Data de nascimento deve estar no passado")
    LocalDate dataNascimento
) {


}
