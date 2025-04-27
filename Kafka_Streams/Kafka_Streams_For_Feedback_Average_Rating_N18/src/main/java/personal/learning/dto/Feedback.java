package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("rating")
	private int rating;
	
	@JsonProperty("comment")
	private String comment;

	public Feedback() {}

	public Feedback(String location, int rating, String comment) {
		this.location = location;
		this.rating = rating;
		this.comment = comment;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Feedback [location=" + location + ", rating=" + rating + ", comment=" + comment + "]";
	}

}
