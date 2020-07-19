package fr.isika.projet4.jwtauthentication.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.projet4.jwtauthentication.model.FavoriteArticle;
import fr.isika.projet4.jwtauthentication.model.ProfilUser;
import fr.isika.projet4.jwtauthentication.model.User;
import fr.isika.projet4.jwtauthentication.repository.FavoriteArticlesRepo;
import fr.isika.projet4.jwtauthentication.repository.ProfilUserRepo;
import fr.isika.projet4.jwtauthentication.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-auth/user/favoriteArticle")
public class ProfilUserController {
	
	@Autowired
	private ProfilUserRepo profilUserRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FavoriteArticlesRepo favoriteArticlesRepo;
	
	@PostMapping("/add")
    void addFavoriteArticle(@RequestBody FavoriteArticle favArticle) {
		favoriteArticlesRepo.save(favArticle);
    }
	
	@GetMapping(value = "/{Articleid}")
	public FavoriteArticle getByidArticle(@PathVariable("Articleid") Long articleId) {
	  return favoriteArticlesRepo.findByArticleId(articleId);
	}
	
	@GetMapping(value = "/exists/{Articleid}")
	public boolean isArticleIdAlreadyHere(@PathVariable("Aticleid") Long articleId) {
	  return favoriteArticlesRepo.existsByArticleId(articleId);
	}
	
	 @GetMapping("/{id}")
	 public ProfilUser getProfilById(@PathVariable("id") Long id) {
		 return null;
		  		}
	
	 @PostMapping("/add/{username}/{articleId}")
    void addArticle(@PathVariable("username") String username, @PathVariable("articleId") Long articleId ) {
	
		
	User user = userRepo.findByUsername(username);
	boolean articleExistsOrNot = favoriteArticlesRepo.existsByArticleId(articleId);
	FavoriteArticle favArticle = new FavoriteArticle();
	if(articleExistsOrNot) {
		favArticle = favoriteArticlesRepo.findByArticleId(articleId);	
	} else {
		favArticle = new FavoriteArticle(articleId);
		favoriteArticlesRepo.save(favArticle);
	}
	
	user.getProfilUser().getFavoriteArticles().add(favArticle);
	profilUserRepo.save(user.getProfilUser());
		
    }
	
	@GetMapping(value = "/findall/{username}")
	public Set<FavoriteArticle> getAllFavoriteArticles(@PathVariable("username") String username) {
		User user = userRepo.findByUsername(username);
		
		Set<FavoriteArticle> articles = user.getProfilUser().getFavoriteArticles();
		return articles;
	}
	
	@DeleteMapping("/delete/{username}/{articleId}")
    void deleterecipe(@PathVariable("username") String username, @PathVariable("articleId") Long articleId) {
	User user = userRepo.findByUsername(username);
	FavoriteArticle favArticle = favoriteArticlesRepo.findByArticleId(articleId);	
	user.getProfilUser().getFavoriteArticles().remove(favArticle);
	profilUserRepo.save(user.getProfilUser());
		
    }
	

}
