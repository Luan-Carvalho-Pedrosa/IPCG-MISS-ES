package tzin_ltda.ipcg_missoes.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
