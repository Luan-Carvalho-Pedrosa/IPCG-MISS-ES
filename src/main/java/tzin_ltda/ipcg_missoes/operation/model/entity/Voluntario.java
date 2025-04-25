package tzin_ltda.ipcg_missoes.operation.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Voluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ocupacao;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;


    public VoluntarioDto toDto(){
        VoluntarioDto dto = new VoluntarioDto(id, null, null, ocupacao, telefone, null);
        if(pessoa != null){
            dto.setPessoa_id(pessoa.getId());
            dto.setNome(pessoa.getNome());
            dto.setCpf(pessoa.getCpf());
        }

        return dto;
    }

  
}
