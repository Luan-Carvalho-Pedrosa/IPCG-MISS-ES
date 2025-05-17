package tzin_ltda.ipcg_missoes.operation.model.request;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AtividadeRequest {
    @NotNull(message = "Insira a data da atividade")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private String relatorio;



    public Atividade toEntity() {
        return new Atividade(null, data, relatorio);
    }
}

