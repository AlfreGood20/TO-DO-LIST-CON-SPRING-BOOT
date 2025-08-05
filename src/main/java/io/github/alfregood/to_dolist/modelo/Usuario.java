package io.github.alfregood.to_dolist.modelo;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false, length = 12)
    private String contrasena;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Tarea> tareas;
}
