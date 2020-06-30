package fr.isika.projet4.article_microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import fr.isika.projet4.article_microservice.model.Article;
import fr.isika.projet4.article_microservice.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api("APi for CRUD operations of article")
@RestController
@RequestMapping(path = "/article-api/public")
public class ArticleController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleRepository articleRepo;

	private WebClient client = WebClient.create("http://localhost:9999");
	private String uriGetArticles;

	
	// WebCLient getAllArticles with optional dateMini
	@ApiOperation(value = "Get all articles with a revision date optional filter")
	@GetMapping(path = "/articles")
//	@ResponseBody
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
	@GetMapping(path = "/article/pmid/{pmid}")
	private Mono<Article> getArticleByPmid(@PathVariable String pmid) {
		 return client.get()
				.uri("/article-api/public/article/pmid/" + pmid)
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
	

//	@ApiOperation(value = "Find aticles by title")
//	public @ResponseBody List<Article> findArticleTitleLike(@PathVariable String articleTitle) {
//		return articleRepo.findByArticleTitleLike("%" + articleTitle + "%");
//	}

}
