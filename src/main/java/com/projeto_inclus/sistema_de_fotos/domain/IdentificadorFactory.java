package com.projeto_inclus.sistema_de_fotos.domain;

import java.util.UUID;

public class IdentificadorFactory {
    public static Identificador criarEmailIdentificador(String email) {
        return new EmailIdentificador(email);
    }

    public static Identificador criarIdIdentificador(UUID id) {
        return new IdIdentificador(id);
    }
}
