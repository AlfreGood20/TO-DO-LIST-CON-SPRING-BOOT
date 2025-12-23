package io.github.alfregood.to_dolist.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.repositorio.UsuarioRepo;

@Service
public class UsuarioDetailsServ implements UserDetailsService{

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByCorreo(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new UsuarioDetails(usuario);
    }
}