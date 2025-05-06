package tzin_ltda.ipcg_missoes.operation.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicEntity;
import tzin_ltda.ipcg_missoes.operation.model.dto.CestaDto;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="cesta_basica")
public class Cesta extends BasicEntity<CestaDto>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="valor")
    private Double valor;
    @Column(name ="data_oferecimento")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "membro_id")
    private Membro membro;

    
    @Override
    public CestaDto toDto() {
        // TODO Auto-generated method stub
        CestaDto cestaDto = new CestaDto(id, valor, data, null);
        if (membro != null) {
            cestaDto.setMembroDto(membro.toDto());
        }

        return cestaDto;
       
    }
    
}
