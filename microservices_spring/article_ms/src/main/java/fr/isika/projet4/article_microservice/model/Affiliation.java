package fr.isika.projet4.article_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Affiliation {
	
	String affiliationPubmed;
	
//	// data after geocoding by Google Maps API
//	String formattedAddress;
//	
//	String latitute;
//	
//	String longitude;

	////////// Constructors \\\\\\\\\\
	
	public Affiliation(String affiliationPubmed) {
		super();
		this.affiliationPubmed = affiliationPubmed;
	}

	public Affiliation() {
		super();
	}

	////////// toString \\\\\\\\\\	
	
	
	@Override
	public String toString() {
		return "Affiliation [affiliationPubmed=" + affiliationPubmed + "]";
	}

	////////// Getters - Setters \\\\\\\\\\
	
	public String getAffiliationPubmed() {
		return affiliationPubmed;
	}

	public void setAffiliationPubmed(String affiliationPubmed) {
		this.affiliationPubmed = affiliationPubmed;
	}

}
