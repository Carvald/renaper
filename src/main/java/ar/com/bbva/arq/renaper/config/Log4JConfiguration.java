package ar.com.bbva.arq.renaper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration@PropertySource(name = "LOGS", value = "file:${syscfgpath}/log4j.properties")
public class Log4JConfiguration {
}
