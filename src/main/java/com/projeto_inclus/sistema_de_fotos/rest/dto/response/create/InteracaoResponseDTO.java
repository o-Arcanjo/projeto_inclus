package com.projeto_inclus.sistema_de_fotos.rest.dto.response.create;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record InteracaoResponseDTO(
        @NotNull
        UUID id,

        @NotBlank(message = "Valor é obrigatório")
        String valor,

        @NotNull
        LocalDateTime dataCriacao,

        TipoInteracaoResponseDTO tipoInteracaoResponseDTO
) {
}
