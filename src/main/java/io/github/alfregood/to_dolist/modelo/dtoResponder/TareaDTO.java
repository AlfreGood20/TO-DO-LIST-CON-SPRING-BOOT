package io.github.alfregood.to_dolist.modelo.dtoResponder;

public record TareaDTO
(
    long id,

    String nombre,

    String descripcion,

    String vencimiento,

    String creacion,

    boolean completado
) {
}