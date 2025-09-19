package com.projeto_inclus.sistema_de_fotos.rest.dto.response.create;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TipoInteracaoResponseDTO(
        @NotNull
        UUID id,

        @NotBlank(message = "tipoInteracao é obrigatório")
        String tipoInteracao
) {
}
