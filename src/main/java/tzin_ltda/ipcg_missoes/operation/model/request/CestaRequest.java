package tzin_ltda.ipcg_missoes.operation.model.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CestaRequest {

    private Double valor;
    @NotNull(message = "A data é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private Long membro_id;
    
    
}
