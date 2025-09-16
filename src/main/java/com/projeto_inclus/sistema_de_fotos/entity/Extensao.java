package com.projeto_inclus.sistema_de_fotos.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Extensao {
    @Id
    @Setter(AccessLevel.NONE)
    @NotNull
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank(message = "tipo_mime é obrigatório")
    @Column(name = "tipo_mime", nullable = false, length = 100)
    private String tipoMime;

    @PrePersist
    private void init(){
        this.id = UUID.randomUUID();
    }

    @OneToMany(mappedBy = "extensao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Foto> fotosComExtensao;

}
