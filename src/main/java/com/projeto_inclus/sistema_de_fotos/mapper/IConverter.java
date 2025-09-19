package com.projeto_inclus.sistema_de_fotos.mapper;

public interface IConverter<DTO,Entidade>{
    DTO converterEmDTO(Entidade entidade);
    Entidade converterEmEntidade(DTO dto);
}
