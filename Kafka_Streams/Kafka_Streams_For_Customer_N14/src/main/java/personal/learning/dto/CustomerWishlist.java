package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerWishlist {
	
	private static final long serialVersionUID = 2L;
	
	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty("itemName")
	private String itemName;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("available")
	private boolean available;
	
	@JsonProperty("timeStamp")
	private String timeStamp;
	
	public CustomerWishlist() {}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "CustomerWishlist [customerName=" + customerName + ", itemName=" + itemName + ", price=" + price
				+ ", location=" + location + ", available=" + available + ", timeStamp=" + timeStamp + "]";
	}
	
}
