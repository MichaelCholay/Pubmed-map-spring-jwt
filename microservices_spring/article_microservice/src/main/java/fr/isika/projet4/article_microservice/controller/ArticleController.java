package fr.isika.projet4.article_microservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.projet4.article_microservice.model.Article;
import fr.isika.projet4.article_microservice.repository.ArticleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("APi for CRUD operations of article")
@RestController
@RequestMapping(path="/article-api/public")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@ApiOperation(value = "Display All articles")
	@GetMapping(path="/articles")
	public @ResponseBody Iterable<Article> getAllArticles(){
		Iterable<Article> articlesIterable = articleRepo.findAll();
		List<Article> articlesList = StreamSupport
				.stream(articlesIterable.spliterator(), false)
				.collect(Collectors.toList());
		return articlesList;
	}
	
	@ApiOperation(value = "Find aticles by title")
	public @ResponseBody List<Article> findArticleTitleLike (@PathVariable String title) {
	return articleRepo.findByTitle("%"+title+"%");
	}
}
