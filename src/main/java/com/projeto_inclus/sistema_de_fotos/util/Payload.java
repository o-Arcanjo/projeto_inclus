package com.projeto_inclus.sistema_de_fotos.util;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Payload (
        UUID id,
        String nome
){
}
