package com.projeto_inclus.sistema_de_fotos.enums;
import com.projeto_inclus.sistema_de_fotos.config.ICarregarPropriedades;


public enum ArquivoPropertie implements ICarregarPropriedades {
    TOKENJWT("tokenJWT.properties");

    private String caminhoArquivo;
    ArquivoPropertie(String caminhoArquivo){
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public String getPropriedades() {
        return caminhoArquivo;
    }
}
