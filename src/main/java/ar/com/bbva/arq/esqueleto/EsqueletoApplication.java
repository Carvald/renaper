package ar.com.bbva.arq.esqueleto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ar.com.bbva.arq.esqueleto", "ar.com.bbva.arq.spring.boot.utils"})
public class EsqueletoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsqueletoApplication.class, args);
	}

}
