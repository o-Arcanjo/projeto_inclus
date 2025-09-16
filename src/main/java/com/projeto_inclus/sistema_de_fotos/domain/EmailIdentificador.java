package com.projeto_inclus.sistema_de_fotos.domain;

import com.projeto_inclus.sistema_de_fotos.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailIdentificador implements Identificador {
    private final String email;

    @Override
    public boolean existeNoRepositorio(IUsuarioRepository repo) {
        return repo.findUsuarioByEmail(email).isPresent();
    }
}
