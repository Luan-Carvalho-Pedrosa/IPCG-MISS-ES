package tzin_ltda.ipcg_missoes.operation.model.request;



import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MaterialRequest {
    @NotNull(message = "Insira o nome do material")
    private String nome;
    private String descricao;

    private Integer quantidade;



    public Material toEntity() {
        return new Material(null, nome, descricao, quantidade);
    }
}

