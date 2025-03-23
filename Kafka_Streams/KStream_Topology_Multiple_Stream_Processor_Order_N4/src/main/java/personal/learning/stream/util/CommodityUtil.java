package personal.learning.stream.util;

import personal.learning.dto.Order;

public class CommodityUtil {
	
	public static Order maskCreditCardNumber(Order order) {
		String creditCardNumber = order.getCreditCardNumber();
		String maskedCreditCardNumber = "**** **** ****" + creditCardNumber.substring(creditCardNumber.length() -4);
		
		return new Order(order.getOrderId(), 
						 order.getOrderLocation(), 
						 order.getCreditCardNumber(),
						 maskedCreditCardNumber,
						 order.getItemName(),
						 order.getPrice(),
						 order.getQuantity());
	}
}
