package io.github.alfregood.to_dolist.modelo.dtoCreacion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TareaCreateDTO
(
    @NotBlank(message = "Este campo es obligatorio")
    String titulo,
    
    @Size(max = 600,message = "No mas de 600 caracteres")
    String descripcion,

    @NotBlank(message = "Este campo es obligatorio")
    String vencimiento

    ) {
}