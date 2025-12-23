package io.github.alfregood.to_dolist.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private TareaServ tareaServ;

    @Autowired
    private UsuarioServ usuarioServ;

    //ENTRA A MENU /menu
    @GetMapping
    public String usuarioIngresado(Model modelo, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        
        Usuario usuario = usuarioServ.buscarPorCorreo(correo);
        List<Tarea> tareas = tareaServ.listaTareas(usuario);   //OBTENER LAS TAREAS DEL USUARIO

        session.setAttribute("USER-ID", usuario.getId());

        //HACE UNA LISTA DE TAREAS IMCOMPLETAS DONDE SI LA TAREA NO ESTA COMPLETA LO GUARDAMOS
        List<Tarea> tareasIncompletas=tareas.stream().filter(tarea-> !tarea.isCompletado()).toList();
        modelo.addAttribute("tarea", new Tarea()); //MANDA UN CONTSRUCTOR PARA HACER NUEVAS TAREAS
        modelo.addAttribute("tareas", tareasIncompletas); // MANDAMOS LAS TAREAS DEL USUARIO
        modelo.addAttribute("usuario", usuario); // MANDAMOS COMO OBJETO AL USUARIO
        return "menu";
    }
}