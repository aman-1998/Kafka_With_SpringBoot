package personal.learning.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("purchaseAmount")
	private String purchaseAmount;
	
	@JsonProperty("gps")
	private String gps;
	
	@JsonProperty("operatingSys")
	private String operatingSys;
	
	public Customer() {}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getOperatingSys() {
		return operatingSys;
	}

	public void setOperatingSys(String operatingSys) {
		this.operatingSys = operatingSys;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", purchaseAmount="
				+ purchaseAmount + ", gps=" + gps + ", operatingSys=" + operatingSys + "]";
	}
	
}