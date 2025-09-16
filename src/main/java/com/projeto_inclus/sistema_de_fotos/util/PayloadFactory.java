package com.projeto_inclus.sistema_de_fotos.util;
import com.projeto_inclus.sistema_de_fotos.entity.Usuario;


public class PayloadFactory {
    public static Payload produzirPayload(Usuario usuario){
        return Payload.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .build();
    }
}
