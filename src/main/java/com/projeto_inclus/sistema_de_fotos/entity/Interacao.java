package com.projeto_inclus.sistema_de_fotos.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "Interacao")
public class Interacao {
    @Id
    @NotNull
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private UUID id;

    @Setter(AccessLevel.NONE)
    @NotNull
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    private void init(){
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDateTime.now();
    }

    @ManyToOne(optional = false)
    private Usuario usuario;

    @OneToOne(mappedBy = "interacao")
    private Comentario comentario;

    @ManyToOne(optional = false)
    private TipoInteracao tipoInteracao;

}
