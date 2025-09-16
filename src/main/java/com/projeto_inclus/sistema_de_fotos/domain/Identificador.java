package com.projeto_inclus.sistema_de_fotos.domain;
import com.projeto_inclus.sistema_de_fotos.repository.IUsuarioRepository;

public interface Identificador {
    boolean existeNoRepositorio(IUsuarioRepository repo);
}
