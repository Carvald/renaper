package ar.com.bbva.arq.esqueleto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import ar.com.bbva.arq.spring.boot.utils.CipherPropertySourceFactory;

@Configuration
@PropertySource(name = "CLAVES", value = "file:${syscfgpath}/claves.properties", factory = CipherPropertySourceFactory.class)
public class ClavesConfiguration {
}
