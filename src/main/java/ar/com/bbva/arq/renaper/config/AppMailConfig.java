package ar.com.bbva.arq.renaper.config;

import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ConfigurationProperties
@PropertySources({  @PropertySource("file:${syscfgpath}/mail.properties") })
@SuppressWarnings("deprecation")
public class AppMailConfig {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.port}")
	private Integer port;

	@Value("${mail.transport.protocol}")
	private String mailTransportProtocol;

	/*@Value("${velocity.template.location}")
	private String velocityTemplateLocation;*/

	@Value("${mail.user}")
	private String mailUser;

	@Value("${mail.password}")
	private String mailPassword;

	/**
	 * Inicializa un mail sender para poder ser injectado en el servicio que implementa el envio de emails
	 * 
	 * @return un JavaMailSender
	 */
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		Session session = Session.getInstance(getMailProperties(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailUser, mailPassword);
			}
		});
		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setSession(session);
		return javaMailSender;
	}

	/**
	 * Inicializa un VelocityEngine para poder utilizar templates en la generacion de emails
	 * 
	 * @return un VelocityEngine
	 * @throws IOException
	 * @throws VelocityException
	 */
	/*@Bean
	public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
		velocityEngineFactoryBean.setResourceLoaderPath(velocityTemplateLocation);
		return velocityEngineFactoryBean.createVelocityEngine();
	}*/

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", mailTransportProtocol);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "false");
		return properties;
	}
}
