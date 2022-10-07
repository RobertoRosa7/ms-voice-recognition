package br.com.cloudmessage.cloudmessage;

import br.com.cloudmessage.cloudmessage.endpoint.MessageEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@EnableScheduling
public class CloudMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMessageApplication.class, args);
	}

	@GetMapping("/")
	public String getConnection() {
		return "The Server is Ok now it's using container";
	}

	@Bean
	public WebMvcConfigurer cors() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping(MessageEndpoint.notificationSubscribe).allowedOrigins("*");
				registry.addMapping(MessageEndpoint.notificationUnsubscribe).allowedOrigins("*");
				registry.addMapping(MessageEndpoint.notificationGetPublicKey).allowedOrigins("*");
				registry.addMapping(MessageEndpoint.file).allowedOrigins("*");
			}
		};
	}

}
