package com.projeto_inclus.sistema_de_fotos.repository;

import com.projeto_inclus.sistema_de_fotos.entity.Extensao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface IExtensaoRepository extends JpaRepository<Extensao, UUID> {
    Optional<Extensao> findExtensaoByTipoMime(String tipoMime);
}
