package fr.isika.projet4.geoloc_microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geoloc {
	
	private Long pmid;
	
	private double latitude;
	
	private double longitude;
	
	

	public Geoloc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Geoloc(Long pmid, double latitude, double longitude) {
		super();
		this.pmid = pmid;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getPmid() {
		return pmid;
	}

	public void setPmid(Long pmid) {
		this.pmid = pmid;
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

	@Override
	public String toString() {
		return "Geoloc [pmid=" + pmid + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	

}
