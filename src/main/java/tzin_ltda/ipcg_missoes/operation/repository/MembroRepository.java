
package tzin_ltda.ipcg_missoes.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {
    
}
