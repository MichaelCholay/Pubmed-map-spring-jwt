package fr.isika.projet4.article_microservice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

	private String lastName;
	
	private String foreName;
	
	private String affiliationPubmed;
	
	private String googleFormatedAdress;
	
	private String email;
	
	private double latitude;
	
	private double longitude;
	
	
	////////// Constructors \\\\\\\\\\

	public Author() {
		super();
	}
	
	

	public Author(String lastName, String foreName, String affiliationPubmed, String googleFormatedAdress, String email,
			double latitude, double longitude) {
		super();
		this.lastName = lastName;
		this.foreName = foreName;
		this.affiliationPubmed = affiliationPubmed;
		this.googleFormatedAdress = googleFormatedAdress;
		this.email = email;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	////////// toString \\\\\\\\\\

	@Override
	public String toString() {
		return "Author [lastName=" + lastName + ", foreName=" + foreName + ", affiliationPubmed=" + affiliationPubmed
				+ ", googleFormatedAdress=" + googleFormatedAdress + ", email=" + email + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
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



	public String getAffiliationPubmed() {
		return affiliationPubmed;
	}



	public void setAffiliationPubmed(String affiliationPubmed) {
		this.affiliationPubmed = affiliationPubmed;
	}



	public String getGoogleFormatedAdress() {
		return googleFormatedAdress;
	}



	public void setGoogleFormatedAdress(String googleFormatedAdress) {
		this.googleFormatedAdress = googleFormatedAdress;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	
	
}
