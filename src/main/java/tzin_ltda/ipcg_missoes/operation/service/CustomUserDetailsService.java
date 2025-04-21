package tzin_ltda.ipcg_missoes.operation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tzin_ltda.ipcg_missoes.operation.model.Usuario;
import tzin_ltda.ipcg_missoes.operation.repository.UsuarioRepositoy;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepositoy usuarioRepositoy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Usuario usuario = this.usuarioRepositoy.findByUsername(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return User.builder()
        .username(usuario.getUsername())
        .password(encoder.encode(usuario.getSenha()))
        .roles(usuario.getPerfil().getNome())
        .build();
    }
    
}
