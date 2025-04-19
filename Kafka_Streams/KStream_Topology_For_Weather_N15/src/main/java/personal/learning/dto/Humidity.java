package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Humidity {
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("humidityPercentage")
	private String humidityPercentage;
	
	public Humidity(String humidityPercentage) {
		this.humidityPercentage = humidityPercentage;
	}
	
	public Humidity() {}

	public String getHumidityPercentage() {
		return humidityPercentage;
	}

	public void setHumidityPercentage(String humidityPercentage) {
		this.humidityPercentage = humidityPercentage;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Humidity [location=" + location + ", humidityPercentage=" + humidityPercentage + "]";
	}
	
}
