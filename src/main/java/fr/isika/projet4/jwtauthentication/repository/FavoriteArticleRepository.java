package fr.isika.projet4.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isika.projet4.jwtauthentication.model.FavoriteArticle;

public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticle, Long> {

}
