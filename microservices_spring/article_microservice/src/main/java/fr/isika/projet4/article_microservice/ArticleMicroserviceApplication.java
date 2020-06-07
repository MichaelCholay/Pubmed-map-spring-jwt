package fr.isika.projet4.article_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ArticleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleMicroserviceApplication.class, args);
	}

}
