package personal.learning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderSummary {
	
	@JsonProperty("itemName")
	private String itemName;
	
	@JsonProperty("totalAmount")
	private int totalAmount;
	
	@JsonProperty("orderDateTime")
	private String orderDateTime;
	
	@JsonProperty("orderLocation")
	private String orderLocation;
	
	@JsonProperty("orderId")
	private int orderId;
	
	public OrderSummary() {}

	public OrderSummary(int orderId, String itemName, int totalAmount, 
						String orderDateTime, String orderLocation) {
		this.orderId = orderId;
		this.itemName = itemName;
		this.totalAmount = totalAmount;
		this.orderDateTime = orderDateTime;
		this.orderLocation = orderLocation;
		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderSummary [itemName=" + itemName + ", totalAmount=" + totalAmount + ", orderDateTime="
				+ orderDateTime + ", orderLocation=" + orderLocation + ", orderId=" + orderId + "]";
	}
	
}
