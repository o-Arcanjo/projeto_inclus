package com.projeto_inclus.sistema_de_fotos.rest.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;


@Builder
public record FotoResponseDTO(
        @NotNull
        UUID id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Url é obrigatória")
        String url,

        @NotNull
        LocalDate dataEnvio,

        ExtensaoResponseDTO extensaoResponseDTO
) {
}
