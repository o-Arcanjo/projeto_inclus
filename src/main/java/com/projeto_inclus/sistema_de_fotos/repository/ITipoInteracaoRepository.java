package com.projeto_inclus.sistema_de_fotos.repository;

import com.projeto_inclus.sistema_de_fotos.entity.TipoInteracao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ITipoInteracaoRepository extends JpaRepository<TipoInteracao, UUID> {
    Optional<TipoInteracao> findTipoInteracaoByTipoInteracao(String tipoInteracao);
}
