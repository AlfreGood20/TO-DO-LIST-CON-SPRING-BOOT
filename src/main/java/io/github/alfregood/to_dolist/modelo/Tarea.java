package io.github.alfregood.to_dolist.modelo;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 600)
    private String descripcion;

    @Column(name="fecha_vencimiento",nullable = false)
    private String fechaVencimiento;

    @Column(name="fecha_creacion")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

    private boolean completado=false;

    @ManyToOne
    private Usuario usuario;
}