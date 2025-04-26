package tzin_ltda.ipcg_missoes.operation.model;

public abstract class BasicDto<T extends BasicEntity, K> {
    
    public abstract T toEntity();

    public abstract K toRequest();
    
}
