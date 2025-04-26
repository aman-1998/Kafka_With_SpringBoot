package personal.learning.dto;

public class TotalRatingCount {
	
	private int sumOfRating;
	
	private int countOfRating;
	
	public TotalRatingCount() {}

	public TotalRatingCount(int sumOfRating, int countOfRating) {
		this.sumOfRating = sumOfRating;
		this.countOfRating = countOfRating;
	}

	public int getSumOfRating() {
		return sumOfRating;
	}

	public void setSumOfRating(int sumOfRating) {
		this.sumOfRating = sumOfRating;
	}

	public int getCountOfRating() {
		return countOfRating;
	}

	public void setCountOfRating(int countOfRating) {
		this.countOfRating = countOfRating;
	}

	@Override
	public String toString() {
		return "TotalRatingCount [sumOfRating=" + sumOfRating + ", countOfRating=" + countOfRating + "]";
	}
	
}
