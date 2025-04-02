package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {
	
	@JsonProperty("feedbackId")
	private int feedbackId;
	
	@JsonProperty("star")
	private int star;
	
	@JsonProperty("comment")
	private String comment;
	
	public Feedback() {}

	public Feedback(int feedbackId, int star, String comment) {
		this.feedbackId = feedbackId;
		this.star = star;
		this.comment = comment;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", star=" + star + ", comment=" + comment + "]";
	}
	
}
