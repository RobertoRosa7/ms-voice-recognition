package br.com.cloudmessage.cloudmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CloudMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudMessageApplication.class, args);
	}

	@GetMapping("/")
	public String getConnection() {
		return "The Server is Ok";
	}

}
