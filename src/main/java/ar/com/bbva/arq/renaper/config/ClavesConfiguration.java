package ar.com.bbva.arq.renaper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ar.com.bbva.arq.spring.boot.utils.CipherPropertySourceFactory;

@Configuration
@ConfigurationProperties
@PropertySource(name = "CLAVES", value = "file:${syscfgpath}/claves.properties", factory = CipherPropertySourceFactory.class)
public class ClavesConfiguration {
	
	private String usuario;
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
