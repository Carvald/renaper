package ar.com.bbva.arq.esqueleto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "SERVICIO_INICIAL", value={"classpath:/servicioInicial.properties"})
public class ServicioInicialConfiguration {
}
