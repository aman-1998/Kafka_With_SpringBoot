package personal.learning.dto;

import java.util.HashMap;
import java.util.Map;

public class CustomerAggregate {
	
	private Map<String, String> shoppingCartItems = new HashMap<>();
	
	private Map<String, String> whishListItems = new HashMap<>();
	
	public CustomerAggregate(Map<String, String> shoppingCartItems, 
			Map<String, String> whishListItems) {
		
		this.shoppingCartItems = shoppingCartItems;
		this.whishListItems = whishListItems;
	}
	
	public CustomerAggregate() {}
	
	public void putShoppingCartItem(String itemName, String timeStamp) {
		shoppingCartItems.put(itemName, timeStamp);
	}
	
	public void putWishItem(String itemName, String timeStamp) {
		whishListItems.put(itemName, timeStamp);
	}

	public Map<String, String> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(Map<String, String> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

	public Map<String, String> getWhishListItems() {
		return whishListItems;
	}

	public void setWhishListItems(Map<String, String> whishListItems) {
		this.whishListItems = whishListItems;
	}

	@Override
	public String toString() {
		return "CustomerAggregate [shoppingCartItems=" + shoppingCartItems 
				+ ", whishListItems=" + whishListItems + "]";
	}
	
}
