package com.projeto_inclus.sistema_de_fotos.middlewares;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MiddlewareContext {
    private String classeOrigem;
    private String variavelAtual;
    private String valorAtual;
}

