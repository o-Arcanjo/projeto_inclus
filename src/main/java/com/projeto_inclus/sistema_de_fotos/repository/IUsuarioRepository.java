package com.projeto_inclus.sistema_de_fotos.repository;

import com.projeto_inclus.sistema_de_fotos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {
        Optional<Usuario> findUsuarioByEmail(String email);
        Optional<Usuario> findUsuarioById(UUID id);
}
