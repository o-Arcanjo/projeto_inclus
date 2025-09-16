package com.projeto_inclus.sistema_de_fotos.rest.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Builder
public record ExtensaoResponseDTO(
        @NotNull
        UUID id,

        @NotBlank(message = "tipoMime é obrigatório")
        String tipoMime
) {
}
