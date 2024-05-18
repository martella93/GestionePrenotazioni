package it.epicode.GestionePrenotazioni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("application.properties")
public class GestionePrenotazioniApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionePrenotazioniApplication.class, args);
	}

}
