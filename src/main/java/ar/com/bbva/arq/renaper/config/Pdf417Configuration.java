package ar.com.bbva.arq.renaper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration("pdf417Properties")
@ConfigurationProperties(prefix = "pdf417.properties")
@PropertySource(name = "PDF147", value={"file:${syscfgpath}/pdf417.properties"})
public class Pdf417Configuration {

	private String maximagenes;
	private String megasdni;
	private String megasfile;
	private String kb;

	public String getMaximagenes() {
		return maximagenes;
	}

	public void setMaximagenes(String maximagenes) {
		this.maximagenes = maximagenes;
	}

	public String getMegasdni() {
		return megasdni;
	}

	public void setMegasdni(String megasdni) {
		this.megasdni = megasdni;
	}

	public String getMegasfile() {
		return megasfile;
	}

	public void setMegasfile(String megasfile) {
		this.megasfile = megasfile;
	}

	public String getKb() {
		return kb;
	}

	public void setKb(String kb) {
		this.kb = kb;
	}

	
	
}
