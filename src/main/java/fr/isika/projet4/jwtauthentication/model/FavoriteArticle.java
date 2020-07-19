package fr.isika.projet4.jwtauthentication.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Document(collection = "articles")
@Entity
@Table(name = "favoriteArticles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long articleId;


	@ManyToMany(mappedBy = "favoriteArticles",fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<ProfilUser> profilUsers;

	////////// Constructors \\\\\\\\\\

	public FavoriteArticle() {
		super();
	}
	
	
	
	
	

	public FavoriteArticle(long articleId) {
		super();
		this.articleId = articleId;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public Set<ProfilUser> getProfilUsers() {
		return profilUsers;
	}

	public void setProfilUsers(Set<ProfilUser> profilUsers) {
		this.profilUsers = profilUsers;
	}


	


	}