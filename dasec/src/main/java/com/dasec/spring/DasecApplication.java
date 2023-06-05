package com.dasec.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*PORYECTO DASEC - Sebastian Dill y David Mañas*/

//Anotación de Spring Boot que configura automáticamente la aplicación
//y la hace ejecutarse como una aplicación Spring Boot.
@SpringBootApplication
public class DasecApplication {	
	// Método principal que es el punto de entrada de la aplicación.
	public static void main(String[] args) {
		// Método que inicia la aplicación Spring Boot. Recibe como parámetros la
		// clase principal de la aplicación y los argumentos de línea de comando.
		SpringApplication.run(DasecApplication.class, args);
	}
}

