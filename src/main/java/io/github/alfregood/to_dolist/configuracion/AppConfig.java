package io.github.alfregood.to_dolist.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {


    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
