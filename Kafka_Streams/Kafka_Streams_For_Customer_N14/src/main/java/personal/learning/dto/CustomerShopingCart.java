package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerShopingCart {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty("itemName")
	private String itemName;
	
	@JsonProperty("noOfItems")
	private int noOfItems;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("timeStamp")
	private String timeStamp;
	
	public CustomerShopingCart() {}

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

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
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

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "CustomerShopingCart [customerName=" + customerName + ", itemName=" + itemName + ", noOfItems="
				+ noOfItems + ", price=" + price + ", location=" + location + ", timeStamp=" + timeStamp + "]";
	}
 
}
