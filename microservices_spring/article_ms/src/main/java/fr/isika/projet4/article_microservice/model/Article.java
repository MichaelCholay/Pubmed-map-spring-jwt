package fr.isika.projet4.article_microservice.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

//@Document(collection = "articles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

//	@Id
//	private ObjectId _id;
	
	private Long pmid;

	private String articleTitle;

	private String journal;

	private LocalDate publicationDate;

	private LocalDate revisionDate;

	//private String[] articleAbstract;

	private String pubmedUrl;
	
//	private String[] keywordsList;
	
	private Author[] authorsList;

	

	////////// Constructors \\\\\\\\\\

	public Article() {
		super();
	}

	public Article(Long pmid, String articleTitle, String journal, LocalDate publicationDate, LocalDate revisionDate,
			String pubmedUrl, Author[] authorsList) {
		super();
		this.pmid = pmid;
		this.articleTitle = articleTitle;
		this.journal = journal;
		this.publicationDate = publicationDate;
		this.revisionDate = revisionDate;
		this.pubmedUrl = pubmedUrl;
		this.authorsList = authorsList;
	}

	////////// toString \\\\\\\\\\

	@Override
	public String toString() {
		return "Article [pmid=" + pmid + ", articleTitle=" + articleTitle + ", journal=" + journal
				+ ", publicationDate=" + publicationDate + ", revisionDate=" + revisionDate + ", pubmedUrl=" + pubmedUrl
				+ ", authorsList=" + Arrays.toString(authorsList) + "]";
	}
	
	
	

	////////// Getters-setters \\\\\\\\\\

	public Long getPmid() {
		return pmid;
	}


	public void setPmid(Long pmid) {
		this.pmid = pmid;
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

//	public String[] getArticleAbstract() {
//		return articleAbstract;
//	}
//
//	public void setArticleAbstract(String[] articleAbstract) {
//		this.articleAbstract = articleAbstract;
//	}

	public String getPubmedUrl() {
		return pubmedUrl;
	}

	public void setPubmedUrl(String pubmedUrl) {
		this.pubmedUrl = pubmedUrl;
	}

//	public String[] getKeywords() {
//		return keywordsList;
//	}
//
//	public void setKeywords(String[] keywords) {
//		this.keywordsList = keywords;
//	}

	public Author[] getAuthors() {
		return authorsList;
	}

	public void setAuthors(Author[] authors) {
		this.authorsList = authors;
	}


	
}