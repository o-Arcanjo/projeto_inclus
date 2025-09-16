package com.projeto_inclus.sistema_de_fotos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;
import java.time.*;

/**
 * Representa a entidade {@code Usuario} no sistema de fotos.
 *
 * Cada usuário possui:
 * - Informações pessoais (nome, email, data de nascimento)
 * - Credenciais de acesso (senha)
 * - Informações de controle (id, data de criação)
 * - Relações com fotos e interações realizadas
 *
 * Esta classe é gerenciada pelo JPA e utiliza Lombok para reduzir código boilerplate.
 *
 * <p><b>Principais decisões de projeto:</b>
 * <ul>
 *   <li>Campos críticos {@code id} e {@code dataCriacao} protegidos contra alterações externas</li>
 *   <li>Senhas armazenadas como hash BCrypt de 60 caracteres</li>
 *   <li>Validação de dados usando Jakarta Validation para integridade de dados</li>
 *   <li>Relações configuradas com fetch LAZY, cascade ALL e orphanRemoval true para consistência</li>
 * </ul>
 * </p>
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(
        name = "Usuario",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_user", columnNames = {"email"})
        }
)
public class Usuario {
    /**
     * Identificador único do usuário (UUID).
     * <p>
     * - Chave primária da entidade.
     * - Incluído no {@code equals/hashCode} para comparação por identidade.
     * - Gerado automaticamente em {@link #init()} no momento da persistência.
     * - Não pode ser alterado manualmente.
     * </p>
     */
    @Id
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private UUID id;

    /**
     * Nome completo do usuário.
     * <p>
     * - Campo obrigatório ({@link NotBlank}).
     * - Tamanho máximo de 150 caracteres ({@link Size} e {@code length = 150}).
     * </p>
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    /**
     * E-mail do usuário.
     * <p>
     * - Campo obrigatório ({@link NotBlank}).
     * - Deve ser um e-mail válido ({@link Email}).
     * - Deve ser único no banco de dados ({@code unique = true}).
     * </p>
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Senha do usuário.
     * <p>
     * - Armazenada como hash BCrypt (60 caracteres).
     * - Campo obrigatório ({@link NotBlank}).
     * - Validado para garantir tamanho exato de 60 caracteres ({@link Size}).
     * - Nunca deve armazenar senha em texto plano.
     * </p>
     */
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 60, max = 60, message = "Senha deve ser um hash BCrypt de 60 caracteres")
    @Column(name = "senha", nullable = false, length = 60, columnDefinition = "CHAR(60)")
    private String senha;

    /**
     * Data de nascimento do usuário.
     * <p>
     * - Campo obrigatório ({@link NotNull}).
     * - Deve estar no passado ({@link Past}).
     * - Útil para validações de idade e regras de negócio.
     * </p>
     */
    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve estar no passado")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    /**
     * Data e hora de criação do registro.
     * <p>
     * - Inicializada automaticamente em {@link #init()}.
     * - Não pode ser alterada manualmente.
     * - Campo obrigatório ({@link NotNull}).
     * </p>
     */
    @Setter(AccessLevel.NONE)
    @NotNull
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    /**
     * Inicializa atributos críticos antes de persistir no banco.
     * <ul>
     *   <li>Gera UUID para o usuário, caso ainda não exista</li>
     *   <li>Define data de criação como {@link LocalDateTime#now()}</li>
     * </ul>
     */
    @PrePersist
    private void init() {
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDateTime.now();
    }

    /**
     * Relação 1:N com {@link Foto}.
     * <p>
     * - Um usuário pode possuir várias fotos.
     * - O lado dono da relação é {@link Foto} (ManyToOne com "usuario").
     * - Fetch LAZY: fotos carregadas apenas quando acessadas.
     * - Cascade ALL: operações de persistência e remoção propagam para fotos.
     * - OrphanRemoval true: fotos removidas da lista são deletadas do banco.
     * </p>
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Foto> fotosArmazenadas = new ArrayList<>();

    /**
     * Relação 1:N com {@link Interacao}.
     * <p>
     * - Um usuário pode realizar várias interações (curtidas, comentários etc).
     * - O lado dono da relação é {@link Interacao} (ManyToOne com "usuario").
     * - Fetch LAZY: interações carregadas apenas quando acessadas.
     * - Cascade ALL: operações de persistência e remoção propagam para interações.
     * - OrphanRemoval true: interações removidas da lista são deletadas do banco.
     * </p>
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interacao> interacaoFotos = new ArrayList<>();
}
