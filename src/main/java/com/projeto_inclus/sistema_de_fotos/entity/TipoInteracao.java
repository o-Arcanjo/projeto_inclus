package com.projeto_inclus.sistema_de_fotos.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoInteracao {
    @Id
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private UUID id;


    @NotBlank(message = "valor é obrigatório")
    @Column(name = "tipoInteracao", length = 100)
    private String tipoInteracao;


    @PrePersist
    private void init(){
        this.id = UUID.randomUUID();
    }

    @OneToMany(mappedBy = "tipoInteracao")
    private List<Interacao> interacoes;

}
