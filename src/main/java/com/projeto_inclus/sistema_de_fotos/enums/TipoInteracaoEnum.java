package com.projeto_inclus.sistema_de_fotos.enums;

import lombok.Getter;

@Getter
public enum TipoInteracaoEnum {
    CURTIDA("CURTIDA"),
    COMENTARIO("COMENTARIO");

    private String tipoInteracao;

    TipoInteracaoEnum(String tipoInteracao){
        this.tipoInteracao = tipoInteracao;
    }

}
