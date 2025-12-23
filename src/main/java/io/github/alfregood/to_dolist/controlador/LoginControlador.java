package io.github.alfregood.to_dolist.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.alfregood.to_dolist.modelo.Login;


@Controller
@RequestMapping("/login")
public class LoginControlador {

    @GetMapping
    public String iniciando(Model modelo) {
        modelo.addAttribute("login", new Login());
        return "login";
    }
}