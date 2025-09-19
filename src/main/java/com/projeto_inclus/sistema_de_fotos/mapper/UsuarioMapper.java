package com.projeto_inclus.sistema_de_fotos.mapper;

import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.DTOFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioMapper<T> implements IConverter<T, Usuario>{

    private final DTOFactory dtoFactory;
    private final Class<T> dtoClass;

    @Override
    public T converterEmDTO(Usuario usuario) {
       return dtoFactory.buildDTO(dtoClass, usuario);
    }

    @Override
    public Usuario converterEmEntidade(T dto) {
        return dtoFactory.buildEntity(dtoClass, dto);
    }

}
