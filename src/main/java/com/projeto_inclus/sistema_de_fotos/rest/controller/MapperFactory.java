package com.projeto_inclus.sistema_de_fotos.rest.controller;

import com.projeto_inclus.sistema_de_fotos.mapper.UsuarioMapper;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateAtualizarUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateLogin;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestCreateUsuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.DTOFactory;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOAtualizar;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.find.UsuarioResponseDTOFind;

import java.util.HashMap;
import java.util.Map;

public class MapperFactory{
private static Map<Class<?>, UsuarioMapper<?>> mappers = new HashMap<>();
    private static final DTOFactory DTOFactory = new DTOFactory();

    static {
    mappers.put(UsuarioResponseDTOCreate.class, new UsuarioMapper<>(DTOFactory, UsuarioResponseDTOCreate.class));
    mappers.put(UsuarioResponseDTOFind.class, new UsuarioMapper<>(DTOFactory, UsuarioResponseDTOFind.class));
    mappers.put(UsuarioRequestCreateUsuario.class, new UsuarioMapper<>(DTOFactory, UsuarioRequestCreateUsuario.class));
    mappers.put(UsuarioRequestCreateLogin.class, new UsuarioMapper<>(DTOFactory, UsuarioRequestCreateLogin.class));
    mappers.put(UsuarioRequestCreateAtualizarUsuario.class, new UsuarioMapper<>(DTOFactory, UsuarioRequestCreateAtualizarUsuario.class));
    mappers.put(UsuarioResponseDTOAtualizar.class, new UsuarioMapper<>(DTOFactory, UsuarioResponseDTOAtualizar.class));

    }

    @SuppressWarnings("unchecked")
    public static <T> UsuarioMapper<T> getMapper(Class<T> dtoClass) {
        UsuarioMapper<T> mapper = (UsuarioMapper<T>) mappers.get(dtoClass);
        if (mapper == null) {
            throw new IllegalArgumentException("Nenhum mapper registrado para: " + dtoClass);
        }
        return mapper;
    }


}
