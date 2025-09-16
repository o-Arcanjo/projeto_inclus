package com.projeto_inclus.sistema_de_fotos.domain;

import com.projeto_inclus.sistema_de_fotos.repository.IUsuarioRepository;
import lombok.*;

import java.util.UUID;

@RequiredArgsConstructor
public class IdIdentificador implements Identificador {
    private final UUID id;

    @Override
    public boolean existeNoRepositorio(IUsuarioRepository repo) {
           return repo.findUsuarioById(id).isPresent();
    }
}
