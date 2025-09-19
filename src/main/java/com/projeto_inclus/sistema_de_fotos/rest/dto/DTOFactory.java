package com.projeto_inclus.sistema_de_fotos.rest.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Fábrica genérica para converter entre DTO (record) e Entidade.
 * Usando <DTO, ENTITY> garante tipagem em tempo de compilação.
 */
public class DTOFactory {

    // Map: DTO class -> builder de DTO (ENTITY -> DTO)
    private final Map<Class<?>, Function<?, ?>> buildersDTO = new HashMap<>();

    // Map: DTO class -> builder de ENTITY (DTO -> ENTITY)
    private final Map<Class<?>, Function<?, ?>> buildersEntity = new HashMap<>();

    /**
     * Registra um builder para conversão ENTITY -> DTO
     * @param dtoClass Classe do DTO
     * @param builder Função que converte ENTITY -> DTO
     * @param <DTO> Tipo do DTO (record)
     * @param <ENTITY> Tipo da entidade
     */
    public <DTO, ENTITY> void registerDTO(Class<DTO> dtoClass, Function<ENTITY, DTO> builder) {
        buildersDTO.put(dtoClass, builder);
    }

    /**
     * Registra um builder para conversão DTO -> ENTITY
     * @param dtoClass Classe do DTO
     * @param builder Função que converte DTO -> ENTITY
     * @param <DTO> Tipo do DTO (record)
     * @param <ENTITY> Tipo da entidade
     */
    public <DTO, ENTITY> void registerEntity(Class<DTO> dtoClass, Function<DTO, ENTITY> builder) {
        buildersEntity.put(dtoClass, builder);
    }

    /**
     * Constrói o DTO a partir da entidade
     */
    @SuppressWarnings("unchecked")
    public <DTO, ENTITY> DTO buildDTO(Class<DTO> dtoClass, ENTITY entity) {
        Function<ENTITY, DTO> builder = (Function<ENTITY, DTO>) buildersDTO.get(dtoClass);
        if (builder == null) {
            throw new IllegalArgumentException("Nenhum builder registrado para o DTO: " + dtoClass);
        }
        return builder.apply(entity);
    }

    /**
     * Constrói a entidade a partir do DTO
     */
    @SuppressWarnings("unchecked")
    public <DTO, ENTITY> ENTITY buildEntity(Class<DTO> dtoClass, DTO dto) {
        Function<DTO, ENTITY> builder = (Function<DTO, ENTITY>) buildersEntity.get(dtoClass);
        if (builder == null) {
            throw new IllegalArgumentException("Nenhum builder registrado para o DTO: " + dtoClass);
        }
        return builder.apply(dto);
    }
}
