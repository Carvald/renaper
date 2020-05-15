package ar.com.bbva.arq.renaper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import ar.com.bbva.arq.spring.boot.utils.CipherPropertySourceFactory;

@Configuration@PropertySource(name = "LOGS", value = "file:${syscfgpath}/log4j.properties")
public class Log4JConfiguration {
}
