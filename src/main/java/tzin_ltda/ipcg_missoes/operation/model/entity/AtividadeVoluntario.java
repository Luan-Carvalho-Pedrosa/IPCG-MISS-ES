package tzin_ltda.ipcg_missoes.operation.model.entity;

import lombok.*;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeVoluntarioDto;

import javax.persistence.*;

@Entity
@Table(name = "atividadevoluntarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AtividadeVoluntario {

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atividadevoluntarios_seq")
    @SequenceGenerator(name = "atividadevoluntarios_seq", sequenceName = "atividade_voluntarios_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    public AtividadeVoluntarioDto toDto(){
        AtividadeVoluntarioDto dto = new AtividadeVoluntarioDto(id, null, null);
        if(atividade != null){
            dto.setAtividadeDto(atividade.toDto());
        }
        if(voluntario != null){
            dto.setVoluntarioDto(voluntario.toDto());
        }



        return dto;

    }
    
}
