package com.projeto_inclus.sistema_de_fotos.util;
import lombok.Builder;

import java.util.Date;

@Builder
public record EstruturaToken(
        Payload payload,
        Date criacaoToken,
        Date expiracaoToken,
        String chaveSecreta
) {

}
