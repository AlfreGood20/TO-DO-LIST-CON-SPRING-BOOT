package io.github.alfregood.to_dolist.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        http
            
            .authorizeHttpRequests((auth) -> auth 
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/clima/**").denyAll()
                .requestMatchers("/menu/**").authenticated()
                .requestMatchers("/registro/**").permitAll()
                .requestMatchers("/tarea/**").authenticated()
                .anyRequest().permitAll()
            )

            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )

            .formLogin((formLogin) -> formLogin
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/menu", true)
                .failureUrl("/login")
                .permitAll()
            )

            .logout((logout) -> logout
                .logoutSuccessUrl("/login")
                .logoutUrl("/menu/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            .httpBasic((httpBasic) -> 
                httpBasic.disable()
            );

        return http.build();
    }
}
