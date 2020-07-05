package fr.isika.projet4.article_microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import fr.isika.projet4.article_microservice.model.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api("API for get requests of articles")
@RestController
@RequestMapping(path = "/article-api/public")
public class ArticleController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private WebClient client = WebClient.create("http://localhost:9999");
	private String uriGetArticles;

	// WebCLient getAllArticles with optional dateMini
	@ApiOperation(value = "Get all articles with a revision date optional filter")
	@GetMapping(path = "/articles")
	private Flux<Article> getAllArticlesWithDateMini(@RequestParam(required = false) String dateMini) {
		if (dateMini != null) {
			uriGetArticles = "/article-api/public/articles?dateMini=" + dateMini;
		} else {
			uriGetArticles = "/article-api/public/articles";
		}
		return client.get()
				.uri(uriGetArticles)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Article.class)
				.log(); 
	}

	// WebCLient get Article by PMID
	@ApiOperation(value = "Find one specific article with its PMID")
	@GetMapping(path = "/article/pmid/{_id}")
	private Mono<Article> getArticleByPmid(@PathVariable String _id) {
		return client.get()
				.uri("/article-api/public/article/pmid/" + _id)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Article.class)
				.log();
	}

	// WebCLient get Articles by title
	@ApiOperation(value = "Find a list of articles with specific words in its title")
	@GetMapping(path = "/articles/title/{wordTitle}")
	private Flux<Article> getArticleByTitle(@PathVariable String wordTitle) {
		return client.get()
				.uri("/article-api/public/articles/title/" + wordTitle)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Article.class)
				.log();
	}

	// WebCLient get Articles by journal
	@ApiOperation(value = "Find a list of articles published in a specific journal")
	@GetMapping(path = "/articles/journal/{journal}")
	private Flux<Article> getArticleByJournal(@PathVariable String journal) {
		return client.get()
				.uri("/article-api/public/articles/journal/" + journal)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Article.class)
				.log();
	}

	// WebCLient get Articles by abstract search
	@ApiOperation(value = "Find a list of articles with specific words in its abstract")
	@GetMapping(path = "/articles/abstract/{wordAbstract}")
	private Flux<Article> getArticleByAbstract(@PathVariable String wordAbstract) {
		return client.get()
				.uri("/article-api/public/articles/abstract/" + wordAbstract)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Article.class)
				.log();
	}

	// WebCLient get Articles by keywords search
	@ApiOperation(value = "Find a list of articles with specific keywords")
	@GetMapping(path = "/articles/keywords/{keyword}")
	private Flux<Article> getArticleByKeyword(@PathVariable String keyword) {
		return client.get()
				.uri("/article-api/public/articles/keywords/" + keyword)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Article.class)
				.log();
	}

	// WebCLient get Articles by author
	@ApiOperation(value = "Find a list of articles of a specific author")
	@GetMapping(path = "/articles/author/{lastName}")
	private Flux<Article> getAllArticlesByAuthor(@PathVariable String lastName,
			@RequestParam(required = false) String foreName) {
		if (foreName != null) {
			uriGetArticles = "/article-api/public/articles/author/" + lastName + "?foreName=" + foreName;
		} else {
			uriGetArticles = "/article-api/public/articles/author/" + lastName;
		}
		return client.get()
				.uri(uriGetArticles)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Article.class)
				.log();
	}

}
