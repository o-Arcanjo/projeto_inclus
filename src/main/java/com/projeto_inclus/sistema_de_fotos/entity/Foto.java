package com.projeto_inclus.sistema_de_fotos.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "Foto", uniqueConstraints = {
      @UniqueConstraint(name = "url_unica", columnNames = "url"),
      @UniqueConstraint(name = "nome_foto_unica", columnNames = "nome")
})
public class Foto {
    @NotNull
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @Id
    UUID id;

    @NotBlank(message = "Url é obrigatória")
    @Lob
    @Column(name = "url", nullable = false, unique = true)
    String url;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome", nullable = false, unique = true, length = 100)
    String nome;

    @NotNull
    @Column(name = "data_envio", nullable = false)
    LocalDate dataEnvio;


    @PrePersist
    private void init(){
        this.id = UUID.randomUUID();
    }

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    private Extensao extensao;
}
