package com.projeto_inclus.sistema_de_fotos.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Comentario")
public class Comentario {
    @NotNull
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Id
    private UUID id;

    @NotBlank(message = "texto é obrigatório")
    @Lob
    @Column(name = "texto", nullable = false)
    String texto;

    @PrePersist
    private void init(){
        this.id = UUID.randomUUID();
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "interacao_fk", referencedColumnName = "id")
    private Interacao interacao;

}
