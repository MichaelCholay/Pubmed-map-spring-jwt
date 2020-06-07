package fr.isika.projet4.article_microservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.isika.projet4.article_microservice.model.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

	List<Article> findByTitle(String title);
	
}
