package io.github.alfregood.to_dolist.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.alfregood.to_dolist.modelo.Tarea;
import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.servicio.TareaServ;
import io.github.alfregood.to_dolist.servicio.UsuarioServ;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tarea")
public class TareaControlador {

    private final TareaServ tareaServ;
    private final UsuarioServ usuarioServ;

    public TareaControlador(TareaServ tareaServ, UsuarioServ usuarioServ) {
        this.tareaServ = tareaServ;
        this.usuarioServ = usuarioServ;
    }

    //CREA NUEVAS TAREA /tarea
    @PostMapping
    public String nuevo(@ModelAttribute Tarea tarea, HttpSession session) {

        Usuario usuario = usuarioServ.obtenerPorId((long) session.getAttribute("USER-ID"));
        tarea.setUsuario(usuario);

        tareaServ.guardar(tarea);
        return "redirect:/menu";
    }

    //ELIMINA TAREA /tarea/{id}/eliminar
     @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable long id) {
        tareaServ.eliminar(id);
        return "redirect:/menu";
    }

    //CAMBIA LA TAREA PENDIENTE COMO COMPLETADO /tarea/{id}/completado
    @GetMapping("/{id}/completar")
    public String cambiarEstadoTarea(@PathVariable long id){
        Tarea tarea=tareaServ.obtenerPorId(id);

        if(tarea!=null){
            tarea.setCompletado(true); //CAMBIAR ES ESTADO DE LA TAREA
            tareaServ.guardar(tarea);  //ACTUALIZA LA TATEA Y LA GUARDA (NO CREA OTRA TAREA IGUAL ACTUALIZA SOLAMENTE)
        }

        return "redirect:/menu";
    }

}
