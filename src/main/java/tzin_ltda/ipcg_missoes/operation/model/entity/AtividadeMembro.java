package tzin_ltda.ipcg_missoes.operation.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "atividademembros")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtividadeMembro {

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atividademembros_seq")
    @SequenceGenerator(name = "atividademembros_seq", sequenceName = "atividade_membros_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Membro membro;

}
