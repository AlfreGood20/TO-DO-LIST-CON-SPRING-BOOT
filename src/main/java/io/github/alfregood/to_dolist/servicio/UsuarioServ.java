package io.github.alfregood.to_dolist.servicio;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.repositorio.UsuarioRepo;

@Service
public class UsuarioServ {

    @Autowired
    private UsuarioRepo repositorio;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario guardar(Usuario usuario) {
        usuario.setContrasena(encoder.encode(usuario.getContrasena()));
        return repositorio.save(usuario);
    }

    public Usuario buscarPorCorreo(String correo){
        return repositorio.findByCorreo(correo).orElseThrow();
    }

    public Usuario obtenerPorId(long id) {
        Optional<Usuario> usuario = repositorio.findById(id);

        if (!usuario.isPresent()) {
            return null;
        }
        return usuario.get();
    }

    public boolean igualarCorreos(String correoUsuario) {
        return repositorio.existsByCorreo(correoUsuario);
    }
}
