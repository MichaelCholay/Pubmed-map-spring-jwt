package fr.isika.projet4.jwtauthentication.response;

import java.util.ArrayList;
import java.util.List;

public class ArticleResponse {
	
	List<String> favoriteArticles = new ArrayList<String>();
	
	

	public ArticleResponse() {
		super();
	}



	public ArticleResponse(List<String> favoriteArticles) {
		super();
		this.favoriteArticles = favoriteArticles;
	}



	public List<String> getFavoriteArticles() {
		return favoriteArticles;
	}



	public void setFavoriteArticles(List<String> favoriteArticles) {
		this.favoriteArticles = favoriteArticles;
	}


	
	

}
