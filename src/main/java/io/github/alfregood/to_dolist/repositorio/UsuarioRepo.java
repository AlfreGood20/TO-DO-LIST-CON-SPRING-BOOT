package io.github.alfregood.to_dolist.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.alfregood.to_dolist.modelo.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}