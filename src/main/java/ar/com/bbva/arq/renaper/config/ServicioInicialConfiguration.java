package ar.com.bbva.arq.renaper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "SERVICIO_INICIAL", value={"file:${syscfgpath}/servicioInicial.properties"})
public class ServicioInicialConfiguration {
}
