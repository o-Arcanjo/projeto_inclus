package com.projeto_inclus.sistema_de_fotos.rest.dto;

import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.create.UsuarioResponseDTOCreate;
import com.projeto_inclus.sistema_de_fotos.rest.dto.response.find.UsuarioResponseDTOFind;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.stream.Collectors;

@Configuration
public class DTOFactoryConfig {

    @Bean
    public DTOFactory dtoFactory() {
        DTOFactory factory = new DTOFactory();

        // Registro para DTO de criação
        factory.register(UsuarioResponseDTOCreate.class, (Usuario usuario) ->
                UsuarioResponseDTOCreate.builder()
                        .id(usuario.getId())
                        .nome(usuario.getNome())
                        .email(usuario.getEmail())
                        .dataNascimento(usuario.getDataNascimento())
                        .dataCriacao(usuario.getDataCriacao())
                        .build()
        );

        // Registro para DTO de busca (detalhado)
        factory.register(UsuarioResponseDTOFind.class, (Usuario usuario) ->
                UsuarioResponseDTOFind.builder()
                        .id(usuario.getId())
                        .nome(usuario.getNome())
                        .email(usuario.getEmail())
                        .dataNascimento(usuario.getDataNascimento())
                        .dataCriacao(usuario.getDataCriacao())
                        .fotosArmazenadas(
                                usuario.getFotosArmazenadas().stream()
                                        .map(foto -> FotoResponseDTO.builder()
                                                .id(foto.getId())
                                                .nome(foto.getNome())
                                                .url(foto.getUrl())
                                                .dataEnvio(foto.getDataEnvio())
                                                .extensaoResponseDTO(
                                                        ExtensaoResponseDTO.builder()
                                                                .id(foto.getExtensao().getId())
                                                                .tipoMime(foto.getExtensao().getTipoMime())
                                                                .build()
                                                )
                                                .build()
                                        ).collect(Collectors.toList())
                        )
                        .interacaoFotos(
                                usuario.getInteracaoFotos().stream()
                                        .map(interacao -> InteracaoResponseDTO.builder()
                                                .id(interacao.getId())
                                                .dataCriacao(interacao.getDataCriacao())
                                                .tipoInteracaoResponseDTO(
                                                        TipoInteracaoResponseDTO.builder()
                                                                .id(interacao.getTipoInteracao().getId())
                                                                .tipoInteracao(interacao.getTipoInteracao().getTipoInteracao())
                                                                .build()
                                                )
                                                .build()
                                        ).collect(Collectors.toList())
                        )
                        .build()
        );

        return factory;
    }
}
