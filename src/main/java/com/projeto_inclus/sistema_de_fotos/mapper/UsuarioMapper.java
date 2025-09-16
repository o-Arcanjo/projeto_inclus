package com.projeto_inclus.sistema_de_fotos.mapper;

import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.DTOFactory;
import com.projeto_inclus.sistema_de_fotos.rest.dto.IDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioMapper<T extends IDTO> implements IConverter<T, Usuario, UsuarioRequestDTO>{

    private final DTOFactory dtoFactory;
    private final Class<T> dtoClass;

    @Override
    public T converterEmDTO(Usuario usuario) {
       return dtoFactory.build(dtoClass, usuario);
    }

    @Override
    public Usuario converterEmEntidade(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setDataNascimento(dto.dataNascimento());
        return usuario;
    }


}
