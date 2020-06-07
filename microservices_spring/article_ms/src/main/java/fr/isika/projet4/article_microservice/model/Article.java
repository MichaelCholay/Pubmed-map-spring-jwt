package fr.isika.projet4.article_microservice.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

@Entity
@PrimaryKeyJoinColumn(name="pmid")
public class Article {
	
	@Id
	private Long pmid;
	
	private String aticleTilte;
	
	private String journal;
	
	private LocalDate publicationDate;
	
	private LocalDate revisionDate;
	
	private String articleAbstract;
	
	private String pubmedUrl;
	
	private String keywords;
	
	
	////////// Getters-setters \\\\\\\\\\

	public Long getPmid() {
		return pmid;
	}

	public void setPmid(Long pmid) {
		this.pmid = pmid;
	}

	public String getAticleTilte() {
		return aticleTilte;
	}

	public void setAticleTilte(String aticleTilte) {
		this.aticleTilte = aticleTilte;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}
