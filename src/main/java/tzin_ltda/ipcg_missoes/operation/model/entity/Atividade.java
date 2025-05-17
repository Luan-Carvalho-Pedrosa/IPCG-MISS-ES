package tzin_ltda.ipcg_missoes.operation.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicEntity;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "atividade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Atividade extends BasicEntity<AtividadeDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atividade_seq")
    @SequenceGenerator(name = "atividade_seq", sequenceName = "atividade_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    private String relatorio;

    @Override
    public AtividadeDto toDto() {
        return AtividadeDto.builder()
                .id(id)
                .data(data)
                .relatorio(relatorio)
                .build();
    }

}
