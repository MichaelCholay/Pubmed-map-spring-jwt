package fr.isika.projet4.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ProfilUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "profileUserArticle_favoriteArticle",
			joinColumns = @JoinColumn(name = "profilUserArticles_id"),
			inverseJoinColumns = @JoinColumn(name = "favoriteArticles_id"))	
	private Set<FavoriteArticle> favoriteArticles;

	public ProfilUser() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<FavoriteArticle> getFavoriteArticles() {
		return favoriteArticles;
	}

	public void setFavoriteArticles(Set<FavoriteArticle> favoriteArticles) {
		this.favoriteArticles = favoriteArticles;
	}

	
	
}
