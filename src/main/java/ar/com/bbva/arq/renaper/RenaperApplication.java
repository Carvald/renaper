package ar.com.bbva.arq.renaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import ar.com.bbva.arq.spring.boot.utils.JNDIProfileHelper;



@SpringBootApplication
@ComponentScan(basePackages = {"ar.com.bbva.arq.renaper", "ar.com.bbva.arq.spring.boot.utils"})
public class RenaperApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RenaperApplication.class, args);
	}


	
		@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RenaperApplication.class);
    }

	
	
	
		/*  @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Estamos corriendo en un servlet container, Websphere application server posiblemente
        // En estos casos cargamos el profile de Spring Boot mediante JNDI
        JNDIProfileHelper.loadProfile();
        return builder.sources(RenaperApplication.class);
    }
	*/

	
}
