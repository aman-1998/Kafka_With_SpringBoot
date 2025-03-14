package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodOrder {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("rate")
	private String rate;
	
	@JsonProperty("resturantName")
	private String resturantName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getResturantName() {
		return resturantName;
	}

	public void setResturantName(String resturantName) {
		this.resturantName = resturantName;
	}

	@Override
	public String toString() {
		return "FoodOrder [id=" + id + ", name=" + name + ", rate=" 
					+ rate + ", resturantName=" + resturantName + "]";
	}
	
}
