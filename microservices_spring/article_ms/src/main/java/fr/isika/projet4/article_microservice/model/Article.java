package fr.isika.projet4.article_microservice.model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {

	@Id
	private ObjectId _id;
	
	private Long pmid;

	private String articleTitle;

	private String journal;

	private LocalDate publicationDate;

	private LocalDate revisionDate;

	private String articleAbstract;

	private String pubmedUrl;

	private List<Document> keywords;

	private List<String> authors;

	////////// Constructors \\\\\\\\\\

	public Article() {
		super();
	}

	public Article(Long pmid, String articleTitle, String journal, LocalDate publicationDate, LocalDate revisionDate,
			String articleAbstract, String pubmedUrl, List<Document> keywords, List<String> authors) {
		super();
		this.pmid = pmid;
		this.articleTitle = articleTitle;
		this.journal = journal;
		this.publicationDate = publicationDate;
		this.revisionDate = revisionDate;
		this.articleAbstract = articleAbstract;
		this.pubmedUrl = pubmedUrl;
		this.keywords = keywords;
		this.authors = authors;
	}

	////////// toString \\\\\\\\\\

	@Override
	public String toString() {
		return "Article [pmid=" + pmid + ", articleTitle=" + articleTitle + ", journal=" + journal
				+ ", publicationDate=" + publicationDate + ", revisionDate=" + revisionDate + ", articleAbstract="
				+ articleAbstract + ", pubmedUrl=" + pubmedUrl + ", keywords=" + keywords + ", authors=" + authors
				+ "]";
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

	public List<Document> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Document> keywords) {
		this.keywords = keywords;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
}