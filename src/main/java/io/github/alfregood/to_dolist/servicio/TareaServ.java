package io.github.alfregood.to_dolist.servicio;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import io.github.alfregood.to_dolist.modelo.Tarea;
import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.repositorio.TareaRepo;

@Service
public class TareaServ {

    private final TareaRepo repositorio;

    public TareaServ(TareaRepo repositorio) {
        this.repositorio = repositorio;
    }

    public Tarea guardar(Tarea tarea){
        return repositorio.save(tarea);
    }

    public List<Tarea> listaTareas(Usuario usuario){
        return usuario.getTareas();
    }

    public void eliminar(long id){
        repositorio.deleteById(id);
    }

    public Tarea obtenerPorId(long id){
        Optional<Tarea> tareaBuscada=repositorio.findById(id);

        if(!tareaBuscada.isPresent()){
            return null;
        }

        return tareaBuscada.get();
    }
}
