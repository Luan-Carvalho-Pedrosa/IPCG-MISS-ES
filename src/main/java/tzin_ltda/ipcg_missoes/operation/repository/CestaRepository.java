package tzin_ltda.ipcg_missoes.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Cesta;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;

import java.util.List;


@Repository
public interface CestaRepository extends JpaRepository<Cesta, Long> {

    public List<Cesta> findByMembro(Membro membro);
    
}
