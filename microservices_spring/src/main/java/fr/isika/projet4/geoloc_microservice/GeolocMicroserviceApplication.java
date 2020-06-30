package fr.isika.projet4.geoloc_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableMongoRepositories(basePackageClasses = ArticleRepository.class)
public class GeolocMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocMicroserviceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
