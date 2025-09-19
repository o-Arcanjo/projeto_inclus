package com.projeto_inclus.sistema_de_fotos.rest.dto.response.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UsuarioResponseDTOAtualizar(
        @NotBlank
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String nome,

        @NotNull
        @Past
        LocalDate dataNascimento
){
}
