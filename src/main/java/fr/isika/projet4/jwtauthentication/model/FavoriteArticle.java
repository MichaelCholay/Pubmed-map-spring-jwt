package fr.isika.projet4.jwtauthentication.model;

import java.time.LocalDate;
import java.util.HashSet;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Document(collection = "articles")
@Entity
@Table(name = "favoriteArticles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteArticle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;

	private String articleTitle;

	private String journal;

	private LocalDate publicationDate;

	private LocalDate revisionDate;

	private String articleAbstract;

	private String pubmedUrl;

	private String keywordsList;

//	private List<Author> authorsList;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "favoriteArticle_user", joinColumns = {
			@JoinColumn(name = "favoriteArticle_id") },
			inverseJoinColumns = { @JoinColumn(name = "user_id")
			})
	
	private Set<User> users = new HashSet<>();

	////////// Constructors \\\\\\\\\\

	public FavoriteArticle() {
		super();
	}

	public FavoriteArticle(Long _id, String articleTitle, String journal, LocalDate publicationDate,
			LocalDate revisionDate, String articleAbstract, String pubmedUrl, String keywordsList/*,
			List<Author> authorsList*/) {
		super();
		this._id = _id;
		this.articleTitle = articleTitle;
		this.journal = journal;
		this.publicationDate = publicationDate;
		this.revisionDate = revisionDate;
		this.articleAbstract = articleAbstract;
		this.pubmedUrl = pubmedUrl;
		this.keywordsList = keywordsList;
//		this.authorsList = authorsList;
	}
	
	// Constructor fo Junit test
	public FavoriteArticle(String articleTitle) {
		super();
		this.articleTitle = articleTitle;
	}

	////////// toString \\\\\\\\\\

	@Override
	public String toString() {
		return "FavoriteArticle [_id=" + _id + ", articleTitle=" + articleTitle + ", journal=" + journal
				+ ", publicationDate=" + publicationDate + ", revisionDate=" + revisionDate + ", articleAbstract="
				+ articleAbstract + ", pubmedUrl=" + pubmedUrl + ", keywordsList=" + keywordsList + /*", authorsList="
				+ authorsList + */"]";
	}

	////////// Getters-setters \\\\\\\\\\

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public LocalDate getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(LocalDate revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public String getPubmedUrl() {
		return pubmedUrl;
	}

	public void setPubmedUrl(String pubmedUrl) {
		this.pubmedUrl = pubmedUrl;
	}

	public String getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(String keywordsList) {
		this.keywordsList = keywordsList;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
//	public List<Author> getAuthorsList() {
//		return authorsList;
//	}
//
//	public void setAuthorsList(List<Author> authorsList) {
//		this.authorsList = authorsList;
//	}

}