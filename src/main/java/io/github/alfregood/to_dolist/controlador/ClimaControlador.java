package io.github.alfregood.to_dolist.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClimaControlador {

    @GetMapping("/clima")
    public String darUrlClima(@RequestParam String ciudad){
        String apiKey=System.getenv("API_KEY_CLIMA");
        String url = "https://api.weatherbit.io/v2.0/current?city="+ciudad+"&key="+apiKey+"&lang=es";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
