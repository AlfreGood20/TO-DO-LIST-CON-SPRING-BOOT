package io.github.alfregood.to_dolist.modelo.dtoResponder;

public record UsuarioDTO
(
    long id,

    String nombre,

    String apellidos,

    int edad,

    String estado,

    String ciudad,

    String correo
) {
}
