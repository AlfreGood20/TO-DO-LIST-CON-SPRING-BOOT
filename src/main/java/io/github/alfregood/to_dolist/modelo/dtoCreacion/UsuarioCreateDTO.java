package io.github.alfregood.to_dolist.modelo.dtoCreacion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreateDTO
(
    @NotBlank(message = "Esta campo es obligatorio")
    String nombre,

    @NotBlank(message = "Este campo es obligatorio")
    String apellidos,

    @Min(value = 10,message = "No puedes ingregar menores de 10 años")
    @Max(value = 95, message = "No puedes ingresar una edad mayor a 95 años")
    int edad,

    @NotBlank(message = "Este campo es obligatorio")
    String estado,

    @NotBlank(message = "Este campo es obligatorio")
    String ciudad,

    @NotBlank(message = "Este campo es obligatorio")
    @Email
    String correo,

    @NotBlank(message = "Este campo es obligatorio")
    @Size(min = 12, message = "La contraseña debe tener al menos 12 caracteres")
    String contrasena
) {

}
