package fr.isika.projet4.jwtauthentication.repository;

import org.springframework.data.repository.CrudRepository;

import fr.isika.projet4.jwtauthentication.model.FavoriteArticle;

public interface FavoriteArticlesRepo extends CrudRepository<FavoriteArticle, Long>{

	FavoriteArticle findByArticleId(Long articleId);

	boolean existsByArticleId(Long articleId);

	

}
