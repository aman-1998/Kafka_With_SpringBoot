package personal.learning.dto;

public class Feedback {
	
	private String location;
	
	private int rating;
	
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
