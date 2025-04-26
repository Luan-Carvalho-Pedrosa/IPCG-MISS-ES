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
import tzin_ltda.ipcg_missoes.operation.model.BasicEntity;
import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Membro extends BasicEntity<MembroDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @Override
    public MembroDto toDto() {
        // TODO Auto-generated method stub
        MembroDto membroDto = new MembroDto(id, null, null, telefone, null);

        if (pessoa != null) {

            membroDto.setNome(pessoa.getNome());
            membroDto.setCpf(pessoa.getCpf());
            membroDto.setPessoa_id(pessoa.getId());

        }
        
        return membroDto;
    }
    
}
