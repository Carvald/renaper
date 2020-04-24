package ar.com.bbva.arq.renaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

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
}
