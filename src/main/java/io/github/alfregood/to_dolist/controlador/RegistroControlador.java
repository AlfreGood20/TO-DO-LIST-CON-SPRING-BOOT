package io.github.alfregood.to_dolist.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.servicio.UsuarioServ;

@Controller
@RequestMapping("/registro")
public class RegistroControlador {

    private final UsuarioServ servicio;

    public RegistroControlador(UsuarioServ servicio) {
        this.servicio = servicio;
    }

    //MOSTRAR MENU REGISTRO /registro
    @GetMapping
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarse";
    }

    //GUARDAR NUEVO USUARIO /registro
    @PostMapping
    public String registrar(@ModelAttribute Usuario usuario,RedirectAttributes atribuError) {
        
        if(servicio.igualarCorreos(usuario.getCorreo())){
            atribuError.addFlashAttribute("error","Ya tienes una cuenta registrada con el correo "+usuario.getCorreo());
            return "redirect:/registro";
        }

        servicio.guardar(usuario);
        return "redirect:/login";
    }  
}
