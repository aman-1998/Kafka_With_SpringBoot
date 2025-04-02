package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderReward {
	
	@JsonProperty("orderId")
	private int orderId;
	
	@JsonProperty("orderLocation")
	private String orderLocation;
	
	@JsonProperty("orderDateTime")
	private String orderDateTime;
	
	@JsonProperty("itemName")
	private String itemName;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("quantity")
	private int quantity;
	
	public OrderReward() {}

	public OrderReward(int orderId, String orderLocation, String orderDateTime, 
			String itemName, String price, int quantity) {
		this.orderId = orderId;
		this.orderLocation = orderLocation;
		this.orderDateTime = orderDateTime;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderReward [orderId=" + orderId + ", orderLocation=" + orderLocation + ", orderDateTime="
				+ orderDateTime + ", itemName=" + itemName + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	 
}
