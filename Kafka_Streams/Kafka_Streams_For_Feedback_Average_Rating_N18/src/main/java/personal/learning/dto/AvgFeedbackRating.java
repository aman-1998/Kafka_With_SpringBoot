package personal.learning.dto;

public class AvgFeedbackRating {
	
	private String location;
	
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
