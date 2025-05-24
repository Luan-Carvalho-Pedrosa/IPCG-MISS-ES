package tzin_ltda.ipcg_missoes.operation.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicEntity;
import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialDto;

import javax.persistence.*;


@Entity
@Table(name = "material")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Material extends BasicEntity<MaterialDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
    @SequenceGenerator(name = "material_seq", sequenceName = "material_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private Integer quantidade;

    @Override
    public MaterialDto toDto() {
        return MaterialDto.builder()
                .id(id)
                .nome(nome)
                .descricao(descricao)
                .quantidade(quantidade)
                .build();
    }

}
