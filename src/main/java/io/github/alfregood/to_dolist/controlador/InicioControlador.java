package io.github.alfregood.to_dolist.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.alfregood.to_dolist.modelo.Login;
import io.github.alfregood.to_dolist.modelo.Usuario;
import io.github.alfregood.to_dolist.servicio.UsuarioServ;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/inicio")
public class InicioControlador {

    
    private final UsuarioServ servicio;

    public InicioControlador (UsuarioServ servicio){
        this.servicio=servicio;
    }

    @GetMapping
    public String iniciando(Model modelo) {
        modelo.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/logiandose")     //AQUI VA EL OBJECTO      //ESTO SIRVE PARA LA COOKIE LA SESSION ACTIVA
    public String iniciarSesion(@ModelAttribute Login login,HttpServletRequest request, RedirectAttributes atribuError) {
        Usuario usuario = servicio.validarCredenciales(login.getCorreo(), login.getContrasena());

        if (usuario == null) {
            atribuError.addFlashAttribute("error","Correo o contraseña icorrectas y/o Sin cuenta registrada");
            return "redirect:/inicio";
        }

        //AQUI VA LA COOKIE
        request.getSession().invalidate(); //CIERRA LA SESION ANTERIOR
        HttpSession nuevaSesion = request.getSession(true); //HABRE UN NUEVA SESSION
        nuevaSesion.setAttribute("USUARIO_ID", usuario.getId()); //MANDAMOS EL ID DEL USUARIO INGRESADO
        
        return "redirect:/menu";
    }

    @GetMapping("/nuevo/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarse";
    }

    @PostMapping("/nuevo/registro/creando")
    public String registrar(@ModelAttribute Usuario usuario) {
        servicio.guardar(usuario);
        return "redirect:/inicio";
    }    
}