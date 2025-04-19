package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Precipitation {
	
	@JsonProperty("location")
	private String location;

	@JsonProperty("precipitationPercentage")
	private String precipitationPercentage;
	
	public Precipitation(String precipitationPercentage) {
		this.precipitationPercentage = precipitationPercentage;
	}
	
	public Precipitation() {}

	public String getPrecipitationPercentage() {
		return precipitationPercentage;
	}

	public void setPrecipitationPercentage(String precipitationPercentage) {
		this.precipitationPercentage = precipitationPercentage;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Precipitation [location=" + location + ", precipitationPercentage=" + precipitationPercentage + "]";
	}
	
}
