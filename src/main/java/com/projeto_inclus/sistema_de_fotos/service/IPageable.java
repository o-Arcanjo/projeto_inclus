package com.projeto_inclus.sistema_de_fotos.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPageable <Entity>{
    Page<Entity> listarEntidadePaginada(Pageable pageable);
}
