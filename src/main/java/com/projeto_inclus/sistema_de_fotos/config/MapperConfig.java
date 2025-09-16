package com.projeto_inclus.sistema_de_fotos.config;

import com.projeto_inclus.sistema_de_fotos.mapper.UsuarioMapper;
import com.projeto_inclus.sistema_de_fotos.rest.dto.request.UsuarioRequestDTO;
import com.projeto_inclus.sistema_de_fotos.rest.dto.DTOFactory;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.find.UsuarioResponseDTOFind;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UsuarioMapper<UsuarioResponseDTOCreate> usuarioCreateResponseMapper(DTOFactory dtoFactory){
        return new UsuarioMapper<>(dtoFactory, UsuarioResponseDTOCreate.class);
    }

    @Bean
    public UsuarioMapper<UsuarioResponseDTOFind> usuarioFindResponseMapper(DTOFactory dtoFactory){
        return new UsuarioMapper<>(dtoFactory, UsuarioResponseDTOFind.class);
    }

    @Bean
    public UsuarioMapper<UsuarioRequestDTO> usuarioCreateRequestMapper(DTOFactory dtoFactory){
        return new UsuarioMapper<>(dtoFactory, UsuarioRequestDTO.class);
    }
}
