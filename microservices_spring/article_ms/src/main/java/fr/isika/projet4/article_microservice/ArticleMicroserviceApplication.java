package fr.isika.projet4.article_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import fr.isika.projet4.article_microservice.repository.ArticleRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories(basePackageClasses = ArticleRepository.class)
public class ArticleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleMicroserviceApplication.class, args);
	}
	
//	@Bean
//    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
// 
//        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
// 
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
// 
//        return mongoTemplate;
// 
//    }

}
