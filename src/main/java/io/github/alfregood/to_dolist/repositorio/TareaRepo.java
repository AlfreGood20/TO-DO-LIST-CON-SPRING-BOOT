package io.github.alfregood.to_dolist.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.alfregood.to_dolist.modelo.Tarea;

@Repository
public interface TareaRepo extends JpaRepository<Tarea,Long>{
}
