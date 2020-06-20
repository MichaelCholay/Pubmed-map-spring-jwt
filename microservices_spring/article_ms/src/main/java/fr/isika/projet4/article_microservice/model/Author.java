package fr.isika.projet4.article_microservice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

	private String lastName;
	
	private String foreName;
	
	private Affiliation affiliation1;
	
	private Affiliation affiliation2;
	
	private String email;
	
	
	////////// Constructors \\\\\\\\\\

	public Author() {
		super();
	}
	
	public Author(String lastName, String foreName, Affiliation affiliation1, Affiliation affiliation2, String email) {
		super();
		this.lastName = lastName;
		this.foreName = foreName;
		this.affiliation1 = affiliation1;
		this.affiliation2 = affiliation2;
		this.email = email;
	}

	////////// toString \\\\\\\\\\

	@Override
	public String toString() {
		return "Author [lastName=" + lastName + ", foreName=" + foreName + ", affiliation1=" + affiliation1
				+ ", affiliation2=" + affiliation2 + ", email=" + email + "]";
	}

	////////// Getters - Setters \\\\\\\\\\


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public Affiliation getAffiliation1() {
		return affiliation1;
	}

	public void setAffiliation1(Affiliation affiliation1) {
		this.affiliation1 = affiliation1;
	}

	public Affiliation getAffiliation2() {
		return affiliation2;
	}

	public void setAffiliation2(Affiliation affiliation2) {
		this.affiliation2 = affiliation2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
