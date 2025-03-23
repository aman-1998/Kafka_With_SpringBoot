package personal.learning.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("orderId")
	private int orderId;
	
	@JsonProperty("orderLocation")
	private String orderLocation;
	
	@JsonProperty("creditCardNumber")
	private String creditCardNumber;
	
	@JsonProperty("orderDateTime")
	private String orderDateTime;
	
	@JsonProperty("itemName")
	private String itemName;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("quantity")
	private String quantity;
	
	public Order() {}

	public Order(int orderId, String orderLocation, String creditCardNumber, 
				 String orderDateTime, String itemName, String price, String quantity) {
		this.orderId = orderId;
		this.orderLocation = orderLocation;
		this.creditCardNumber = creditCardNumber;
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

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderLocation=" + orderLocation + ", creditCardNumber="
				+ creditCardNumber + ", orderDateTime=" + orderDateTime + ", itemName=" + itemName + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
	
}