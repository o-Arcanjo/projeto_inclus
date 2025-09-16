package com.projeto_inclus.sistema_de_fotos.rest.dto.response.find;

import com.projeto_inclus.sistema_de_fotos.rest.dto.FotoResponseDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.InteracaoResponseDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.IDTO;
import lombok.Builder;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



/**
 * Dto Usado para retornar uma resposta ao usuário
 *
 * */
@Builder
public record UsuarioResponseDTOFind(
        @NotNull(message = "UUID deve ter exatamento 32 caracteres")
        UUID id,

        @NotBlank
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
        String nome,

        @NotBlank(message="Email é obrigatório")
        @Email(message="Email deve ser válido")
        String email,

        @NotNull
        @Past
        LocalDate dataNascimento,

        @NotNull
        LocalDateTime dataCriacao,

        List<FotoResponseDTO> fotosArmazenadas,

        List<InteracaoResponseDTO> interacaoFotos
) implements IDTO {
}
