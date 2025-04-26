package personal.learning.stream.util;

import personal.learning.dto.Order;
import personal.learning.dto.OrderReward;
import personal.learning.dto.OrderSummary;

public class OrderUtil {
	
	public static Order maskCreditCardNumber(Order order) {
		String creditCardNumber = order.getCreditCardNumber();
		String maskedCreditCardNumber = "**** **** ****" + creditCardNumber.substring(creditCardNumber.length() - 4);
		
		return new Order(order.getOrderId(), 
						 order.getOrderLocation(), 
						 maskedCreditCardNumber,
						 order.getOrderDateTime(),
						 order.getItemName(),
						 order.getPrice(),
						 order.getQuantity());
	}
	
	public static OrderSummary convertToOrderSummary(Order maskedCreditCardOrder) {
		
		int totalAmount = maskedCreditCardOrder.getQuantity() * Integer.parseInt(maskedCreditCardOrder.getPrice());
		
		return new OrderSummary(maskedCreditCardOrder.getOrderId(), maskedCreditCardOrder.getItemName(), 
								totalAmount, maskedCreditCardOrder.getOrderDateTime(), 
								maskedCreditCardOrder.getOrderLocation());
	}
	
	public static OrderReward convertToOrderReward(Order maskedCreditCardOrder) {
		
		return new OrderReward(maskedCreditCardOrder.getOrderId(), 
				 maskedCreditCardOrder.getOrderLocation(), 
				 maskedCreditCardOrder.getOrderDateTime(),
				 maskedCreditCardOrder.getItemName(),
				 maskedCreditCardOrder.getPrice(),
				 maskedCreditCardOrder.getQuantity());
	}
}
