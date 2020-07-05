package fr.isika.projet4.article_microservice.repository;

import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import fr.isika.projet4.article_microservice.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {

	Article findBy_id(Long _id);
	
	List<Article> findByArticleTitleLike(String articleTitle);
	
	//List<Article> findByLocationWithin(Circle circle); //{"location" : {"$within" : {"$center" : [ [x, y], distance]}}}

}
