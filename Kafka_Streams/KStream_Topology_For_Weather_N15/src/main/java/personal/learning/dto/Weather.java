package personal.learning.dto;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("humidityPercentage")
	private String humidityPercentage = StringUtils.EMPTY;
	
	@JsonProperty("precipitationPercentage")
	private String precipitationPercentage = StringUtils.EMPTY;
	
	@JsonProperty("rainPossibility")
	private boolean rainPossibility;
	
	@JsonProperty("comment")
	private String comment;
	
	public Weather() {}

	public Weather(String humidityPercentage, String precipitationPercentage, boolean rainPossibility, String comment) {
		this.humidityPercentage = humidityPercentage;
		this.precipitationPercentage = precipitationPercentage;
		this.rainPossibility = rainPossibility;
		this.comment = comment;
	}

	public String getHumidityPercentage() {
		return humidityPercentage;
	}

	public void setHumidityPercentage(String humidityPercentage) {
		this.humidityPercentage = humidityPercentage;
	}

	public String getPrecipitationPercentage() {
		return precipitationPercentage;
	}

	public void setPrecipitationPercentage(String precipitationPercentage) {
		this.precipitationPercentage = precipitationPercentage;
	}

	public boolean isRainPossibility() {
		return rainPossibility;
	}

	public void setRainPossibility(boolean rainPossibility) {
		this.rainPossibility = rainPossibility;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Weather [location=" + location + ", humidityPercentage=" + humidityPercentage
				+ ", precipitationPercentage=" + precipitationPercentage + ", rainPossibility=" + rainPossibility
				+ ", comment=" + comment + "]";
	}

}
