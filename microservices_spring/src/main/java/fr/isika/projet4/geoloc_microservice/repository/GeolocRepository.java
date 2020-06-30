package fr.isika.projet4.geoloc_microservice.repository;

import java.util.List;

import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import fr.isika.projet4.geoloc_microservice.model.Article;
import fr.isika.projet4.geoloc_microservice.model.Geoloc;

public interface GeolocRepository extends MongoRepository<Geoloc, String> {

	Geoloc findByPmid(Long pmid);
	
//	List<Article> findByArticleTitleLike(String articleTitle);
	
	//List<Article> findByLocationWithin(Circle circle); //{"location" : {"$within" : {"$center" : [ [x, y], distance]}}}

}
