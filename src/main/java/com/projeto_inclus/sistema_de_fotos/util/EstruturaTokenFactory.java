package com.projeto_inclus.sistema_de_fotos.util;

import com.projeto_inclus.sistema_de_fotos.config.CarregarPropriedades;
import com.projeto_inclus.sistema_de_fotos.enums.ArquivoPropertie;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Properties;

public class EstruturaTokenFactory {
    private static final Properties PROPRIEDADES_JWT;

    static {
        try {
            PROPRIEDADES_JWT = CarregarPropriedades.carregar(ArquivoPropertie.TOKENJWT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final long EXPIRACAO_EM_HORAS = 24L;

    private EstruturaTokenFactory() {}

    public static EstruturaToken produzirEstruturaParaToken(Payload payload) {
        // 1. Obtenha o momento atual usando a API moderna (Instant).
        Instant agora = Instant.now();

        // 2. Calcule o momento da expiração.
        Instant expiracao = agora.plus(EXPIRACAO_EM_HORAS, ChronoUnit.HOURS);

        // 3. Converta os Instants para Date para usar no seu record.
        Date dataCriacao = Date.from(agora);
        Date dataExpiracao = Date.from(expiracao);

        // 4. Construa a EstruturaToken com os objetos Date.
        return EstruturaToken.builder()
                .payload(payload)
                .criacaoToken(dataCriacao)
                .expiracaoToken(dataExpiracao)
                .chaveSecreta(PROPRIEDADES_JWT.getProperty("CHAVE_SECRETA"))
                .build();
    }
}
