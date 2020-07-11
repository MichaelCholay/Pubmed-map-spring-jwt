package fr.isika.projet4.geoloc_microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import fr.isika.projet4.geoloc_microservice.model.Article;
import fr.isika.projet4.geoloc_microservice.model.Geoloc;
import fr.isika.projet4.geoloc_microservice.repository.GeolocRepository;
import io.swagger.annotations.Api;
import reactor.core.publisher.Flux;

@Api("APi for CRUD operations of article")
@RestController
@RequestMapping(path = "/geoloc-api/public")
public class GeolocController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GeolocRepository geolocRepo;

//	@Autowired
//	private RestTemplate restTemplate;

//	@Autowired
//	private Article article;

//	@ApiOperation(value = "Display All articles in MongoDb")
//	@GetMapping(path = "/articlesMongo")
//	public @ResponseBody Iterable<Article> getAllArticlesMongoDb() {
//
//		Iterable<Article> articlesIterable = articleRepo.findAll();
//		List<Article> articlesList = StreamSupport.stream(articlesIterable.spliterator(), false)
//				.collect(Collectors.toList());
//		return articlesList;
//	}

	private WebClient client = WebClient.create("http://localhost:9999");

	@GetMapping(path = "/geoloc")
	private Flux<Geoloc> getAllGeoloc() {
//	 final String uri = "http://localhost:9999/article-api/public/geoloc";

	// WebCLient
	 	return client.get()
				.uri("/article-api/public/geoloc")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Geoloc.class)
				.log();
	}
	
}
