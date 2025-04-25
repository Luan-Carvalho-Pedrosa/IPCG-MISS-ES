package tzin_ltda.ipcg_missoes.operation.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    private Long id;
    private String nome;
    private String username;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    
}
