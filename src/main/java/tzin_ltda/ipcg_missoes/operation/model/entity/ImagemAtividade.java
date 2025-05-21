package tzin_ltda.ipcg_missoes.operation.model.entity;

import lombok.*;
import tzin_ltda.ipcg_missoes.operation.model.dto.ImagemAtividadeDto;

import javax.persistence.*;

@Entity
@Table(name = "imagens_atividade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ImagemAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagens_atividade_seq")
    @SequenceGenerator(name = "imagens_atividade_seq", sequenceName = "imagens_atividade_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "imagem",columnDefinition = "bytea" )
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    public ImagemAtividadeDto toDto() {
        ImagemAtividadeDto dto = new ImagemAtividadeDto(id, imagem, null);

        if (atividade != null) {
            dto.setAtividadeDto(atividade.toDto());
        }

        return dto;
    }

}
