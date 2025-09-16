package com.projeto_inclus.sistema_de_fotos.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CarregarPropriedades {
    private CarregarPropriedades(){};
    public static <T extends ICarregarPropriedades> Properties carregar(T arquivoComPropriedades) throws IOException{
        Properties props = new Properties();
        String path = arquivoComPropriedades.getPropriedades();
        try(InputStream input = CarregarPropriedades.class.getResourceAsStream("/" + path)){
            if(input == null){
                throw new IOException("Arquivo do tipo properties não encontrado");
            }
            props.load(input);
        }
        return props;
    }
}
