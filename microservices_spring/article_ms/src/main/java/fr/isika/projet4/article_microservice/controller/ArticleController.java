package fr.isika.projet4.article_microservice.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import fr.isika.projet4.article_microservice.model.Article;
import fr.isika.projet4.article_microservice.model.Articles;
import fr.isika.projet4.article_microservice.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("APi for CRUD operations of article")
@RestController
@RequestMapping(path = "/article-api/public")
public class ArticleController {
	

	@Autowired
	private ArticleRepository articleRepo;

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

	WebClient webClient = WebClient.create();

	@GetMapping(path = "/articles")
	private Article[] getAllArticles() {
	 final String uri = "http://localhost:9999/article-api/public/articles";

//		URL url = new URL("http://localhost:9999/article-api/public/articles");
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.setRequestMethod("GET");

	// RestTemplate String
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    System.out.println("StringJson: " + result);
//	    return result;

	// RestTemplate String with HttpHeaders
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	
	//Gson
	    //Gson gson = new Gson();
	    final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
	    Article[] articleArray = gson.fromJson(result, Article[].class);
	    System.out.println("Gson : " + articleArray.toString());
	    return articleArray;

	// WebCLient String
//	@GetMapping(path = "/articles")
//	public Flux<ArticlesList> listArticles() {
//		return webClient.get().uri("http://localhost:9999/article-api/public/articles").retrieve().bodyToFlux(ArticlesList.class);
	}

	@ApiOperation(value = "Find aticles by title")
	public @ResponseBody List<Article> findArticleTitleLike(@PathVariable String articleTitle) {
		return articleRepo.findByArticleTitleLike("%" + articleTitle + "%");
	}

}
