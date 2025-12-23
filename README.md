# TO-DO LIST 📒🖊️
## DEPENDENCIAS USADAS
````xml
<dependencies>

    <!-- Spring JPA persistencia de datos, en la Base De Datos-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

    <!-- Spring security manejo de sessiones, login y logout -->
		<dependency> 
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

    <!-- Spring thymeleaf manejo de motor de platilla-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>

    <!-- Spring starter web para manejo de anotaciones web -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

    <!-- Para poder hacer la conexion con la base de datos -->
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>

    <!-- Para manejo de anotaciones getter, setter, builder etc -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

    <!-- Spring test para hacer test por defecto lo trae -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
</dependencies>
````

## CONFIGURACION SPRING SECURITY
````java

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        http
            //Manejo de request permisos
            .authorizeHttpRequests((auth) -> auth 
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/clima/**").denyAll()
                .requestMatchers("/menu/**").authenticated()
                .requestMatchers("/registro/**").permitAll()
                .requestMatchers("/tarea/**").authenticated()
                .anyRequest().permitAll()
            )
            // Indicar que se usara la app con estado
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            // Configuración login
            .formLogin((formLogin) -> formLogin
                .loginPage("/login") // Donde estara el login
                .loginProcessingUrl("/login") // A donde va ir cuando se louiarse
                .defaultSuccessUrl("/menu", true) // Depues de loguiarse
                // Indicar los parametros que usara del login (opcional)
                .usernameParameter("correo") 
                .passwordParameter("contrasena")
                // Si falla el login donde va ir
                .failureUrl("/login?error")
                // Permitir el acceso
                .permitAll()
            )

            // Configuración logout
            .logout((logout) -> logout
                .logoutSuccessUrl("/login") // Al hacer logout (Cerrar sesion) donde ira
                .logoutUrl("/logout") // Indicar donde estara el logout
                .invalidateHttpSession(true) // Invalidar la session activa del login anterior 
                .clearAuthentication(true) // Limpiar el contexto de spring security
                .deleteCookies("JSESSIONID") // Borrar sesion cookies de la session
                .permitAll() // Permitir acceso
            )

            // Desactivar login basico por defecto de spring security
            .httpBasic((httpBasic) -> 
                httpBasic.disable()
            );

        return http.build(); // Construir filtro
    }
}

````

## ENTIDAD RELACION MYSQL 
<img width="794" height="435" alt="image" src="https://github.com/user-attachments/assets/e53930ea-93b0-4ef4-8948-d5d2a88a6f56" />

## PARA USAR ESTA PAGINA WEB DEBES TENER SUS VARIABLES DE ENTORNO
- Si no sabes configurar varibales de entorno -> <a href="https://kastroud.hashnode.dev/adding-env-variables-to-your-spring-boot-project-on-vs-code">Click Aqui</a>
- Variable que debes tener: 
  - username (db)
  - password (db)
  - url (db)
  - api key (clima)

- Java ➕17
- Mysql ➕8.0

## PARA USAR LA API DEL CLIMA
- Registrarte en <a href="https://www.weatherbit.io/account/login">Click aqui</a>
- UNA VEZ OBTENIDA LA API KEY PONERLO COMO VARIABLE DE ENTONO PARA PODER USARLO

## PRUEBA APP WEB
👉
<a href="https://to-do-list-con-spring-boot.onrender.com/login">CLICK AQUI</a>


## Login
<img width="1348" height="590" alt="image" src="https://github.com/user-attachments/assets/d0a9f609-53b0-441c-a926-4256d1553456" />


## Menu
<img width="1359" height="591" alt="image" src="https://github.com/user-attachments/assets/2895c2b7-3271-4a42-920d-921f89098692" />

## Formulario nueva tarea
<img width="1351" height="596" alt="image" src="https://github.com/user-attachments/assets/fee61c76-28c9-4102-b174-a90593259867" />
<img width="1357" height="587" alt="image" src="https://github.com/user-attachments/assets/d3fa470a-30ed-4137-bc04-0880bce02c39" />

## Calendario
<img width="1349" height="600" alt="image" src="https://github.com/user-attachments/assets/26b80625-ab32-4bad-a6af-c10c4ae4c938" />


#
Ultima actualización: 23/12/25.