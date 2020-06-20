package fr.isika.projet4.article_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Affiliation {
	
	String affiliationPubmed;
	
	// data after geocoding by Google Maps API
	String formattedAddress;
	
	String latitute;
	
	String longitude;

	////////// Constructors \\\\\\\\\\
	
	public Affiliation(String affiliationPubmed, String formatted_address, String latitute, String longitude) {
		super();
		this.affiliationPubmed = affiliationPubmed;
		this.formattedAddress = formatted_address;
		this.latitute = latitute;
		this.longitude = longitude;
	}

	public Affiliation() {
		super();
	}

	////////// toString \\\\\\\\\\	
	
	
	@Override
	public String toString() {
		return "Affiliation [affiliationPubmed=" + affiliationPubmed + ", formattedAddress=" + formattedAddress
				+ ", latitute=" + latitute + ", longitude=" + longitude + "]";
	}

	////////// Getters - Setters \\\\\\\\\\
	
	public String getAffiliationPubmed() {
		return affiliationPubmed;
	}

	public void setAffiliationPubmed(String affiliationPubmed) {
		this.affiliationPubmed = affiliationPubmed;
	}

	public String getFormatted_address() {
		return formattedAddress;
	}

	public void setFormatted_address(String formatted_address) {
		this.formattedAddress = formatted_address;
	}

	public String getLatitute() {
		return latitute;
	}

	public void setLatitute(String latitute) {
		this.latitute = latitute;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}	
}
