package tzin_ltda.ipcg_missoes.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.Usuario;

@Repository
public interface UsuarioRepositoy extends JpaRepository<Usuario, Long> {
    
    public Usuario findByUsername(String username);
    
}
