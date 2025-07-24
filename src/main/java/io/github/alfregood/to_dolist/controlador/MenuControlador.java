package io.github.alfregood.to_dolist.controlador;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.github.alfregood.to_dolist.modelo.Tarea;
import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.servicio.TareaServ;
import io.github.alfregood.to_dolist.servicio.UsuarioServ;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/menu")
public class MenuControlador {

    private final TareaServ servicio;

    private final UsuarioServ usuarioServ;

    public MenuControlador(TareaServ servicio, UsuarioServ usuarioServ) {
        this.servicio = servicio;
        this.usuarioServ = usuarioServ;
    }

    @GetMapping
    public String usuarioIngresado(Model modelo, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("USUARIO_ID"); // OBETNER ID DEL USUARIO INGRESADO

        if (usuarioId == null) {
            return "redirect:/inicio"; // SI NO HAY ID REDIRIGIR A INICIO PARA EL INICIO DE SESION
        }

        Usuario usuario = usuarioServ.obtenerPorId(usuarioId); // RECIBIR EL USUARIO
        List<Tarea> tareas = servicio.listaTareas(usuario);   //OBTENER LAS TAREAS DEL USUARIO

        //HACE UNA LISTA DE TAREAS IMCOMPLETAS DONDE SI LA TAREA NO ESTA COMPLETA LO GUARDAMOS
        List<Tarea> tareasIncompletas=tareas.stream().filter(tarea-> !tarea.isCompletado()).toList();

        
        modelo.addAttribute("tarea", new Tarea()); //MANDA UN CONTSRUCTOR PARA HACER NUEVAS TAREAS
        modelo.addAttribute("tareas", tareasIncompletas); // MANDAMOS LAS TAREAS DEL USUARIO
        modelo.addAttribute("usuario", usuario); // MANDAMOS COMO OBJETO AL USUARIO
        return "menu";
    }

    @GetMapping("/eliminar/tarea")
    public String eliminarTarea(@RequestParam long id) {
        servicio.eliminar(id);
        return "redirect:/menu";
    }

    @GetMapping("/completado")
    public String cambiarEstadoTarea(@RequestParam long id){
        Tarea tarea=servicio.obtenerPorId(id);

        if(tarea!=null){
            tarea.setCompletado(true); //CAMBIAR ES ESTADO DE LA TAREA
            servicio.guardar(tarea);  //ACTUALIZA LA TATEA Y LA GUARDA (NO CREA OTRA TAREA IGUAL ACTUALIZA SOLAMENTE)
        }

        return "redirect:/menu";
    }

    @PostMapping("/nuevo/tarea")
    public String crearNuevaTarea(@ModelAttribute Tarea tarea, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("USUARIO_ID");

        if (usuarioId == null) {
            return "redirect:/inicio";
        }

        Usuario usuario = usuarioServ.obtenerPorId(usuarioId);
        tarea.setUsuario(usuario);

        servicio.guardar(tarea);
        return "redirect:/menu";
    }

    @GetMapping("/cerrandoSesion")
    public String cerraSesion(HttpSession session) {

        Long usuarioId = (Long) session.getAttribute("USUARIO_ID");

        if (usuarioId == null) {
            return "redirect:/inicio";
        }

        session.invalidate();
        return "redirect:/inicio";
    }

}