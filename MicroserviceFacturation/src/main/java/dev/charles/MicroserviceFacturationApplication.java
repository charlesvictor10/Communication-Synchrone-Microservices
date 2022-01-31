package dev.charles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceFacturationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceFacturationApplication.class, args);
	}

}
