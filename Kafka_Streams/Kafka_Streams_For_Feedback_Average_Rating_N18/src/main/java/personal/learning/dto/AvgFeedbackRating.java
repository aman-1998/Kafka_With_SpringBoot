package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AvgFeedbackRating {
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("avgRating")
	private double avgRating;
	
	public AvgFeedbackRating(String location, double avgRating) {
		this.location = location;
		this.avgRating = avgRating;
	}
	
	public AvgFeedbackRating() {}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	@Override
	public String toString() {
		return "AvgFeedbackRating [location=" + location + ", avgRating=" + avgRating + "]";
	}

}
