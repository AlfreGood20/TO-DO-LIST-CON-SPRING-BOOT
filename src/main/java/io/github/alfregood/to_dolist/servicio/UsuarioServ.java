package io.github.alfregood.to_dolist.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.repositorio.UsuarioRepo;

@Service
public class UsuarioServ {

    @Autowired
    private UsuarioRepo repositorio;

    public Usuario guardar(Usuario usuario) {
        return repositorio.save(usuario);
    }

    public Usuario validarCredenciales(String correo, String contrasena) {
        Optional<Usuario> usuario = repositorio.findByCorreoAndContrasena(correo, contrasena);

        if (!usuario.isPresent()) {
            return null;
        }

        return usuario.get();
    }

    public Usuario obtenerPorId(long id) {
        Optional<Usuario> usuario = repositorio.findById(id);

        if (!usuario.isPresent()) {
            return null;
        }
        return usuario.get();
    }

    public boolean igualarCorreos(String correoUsuario) {
        List<String> correos = repositorio.findAll().stream().map(u -> u.getCorreo()).collect(Collectors.toList());

        for (String correo : correos) {
            if (correo.equalsIgnoreCase(correoUsuario)) {
                return true;
            }
        }

        return false;
    }
}
