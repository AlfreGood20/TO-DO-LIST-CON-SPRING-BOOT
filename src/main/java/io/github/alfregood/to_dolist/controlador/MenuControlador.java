package io.github.alfregood.to_dolist.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.alfregood.to_dolist.modelo.Tarea;
import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.servicio.TareaServ;
import io.github.alfregood.to_dolist.servicio.UsuarioServ;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/menu")
public class MenuControlador {

    private final UsuarioServ usuarioServ;
    private final TareaServ tareaServ;

     public MenuControlador(UsuarioServ usuarioServ, TareaServ tareaServ) {
        this.usuarioServ = usuarioServ;
        this.tareaServ = tareaServ;
    }

    //ENTRA A MENU /menu
    @GetMapping
    public String usuarioIngresado(Model modelo, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("USUARIO_ID"); // OBETNER ID DEL USUARIO INGRESADO

        if (usuarioId == null) {
            return "redirect:/login"; // SI NO HAY ID REDIRIGIR A INICIO PARA EL INICIO DE SESION
        }

        Usuario usuario = usuarioServ.obtenerPorId(usuarioId); // RECIBIR EL USUARIO
        List<Tarea> tareas = tareaServ.listaTareas(usuario);   //OBTENER LAS TAREAS DEL USUARIO

        //HACE UNA LISTA DE TAREAS IMCOMPLETAS DONDE SI LA TAREA NO ESTA COMPLETA LO GUARDAMOS
        List<Tarea> tareasIncompletas=tareas.stream().filter(tarea-> !tarea.isCompletado()).toList();

        modelo.addAttribute("tarea", new Tarea()); //MANDA UN CONTSRUCTOR PARA HACER NUEVAS TAREAS
        modelo.addAttribute("tareas", tareasIncompletas); // MANDAMOS LAS TAREAS DEL USUARIO
        modelo.addAttribute("usuario", usuario); // MANDAMOS COMO OBJETO AL USUARIO
        return "menu";
    }

    //CIERRA SESION /menu/
    @GetMapping("/")
    public String cerraSesion(HttpSession session) {

        Long usuarioId = (Long) session.getAttribute("USUARIO_ID");

        if (usuarioId == null) {
            return "redirect:/login";
        }

        session.invalidate();
        return "redirect:/login";
    }
}
