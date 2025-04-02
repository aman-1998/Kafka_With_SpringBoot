package personal.learning.stream.util;

import java.util.Base64;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;

import personal.learning.dto.Order;
import personal.learning.dto.OrderReward;
import personal.learning.dto.OrderSummary;

public class OrderUtil {
	
	public static void fraudApi() {
		System.out.println("Simulate Calling API for fraud");
	}
	
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
	
	public static KeyValueMapper<String, Order, KeyValue<String, OrderReward>> convertToOrderRewardChangeKey() {
		return (key, order) -> KeyValue.pair(String.valueOf(order.getOrderId()) + "_" + order.getOrderLocation(),
										     convertToOrderReward(order));
	}
	
	public static Predicate<String, OrderSummary> isPlastic() {
		return (key, orderSummary) -> orderSummary.getItemName().toUpperCase().startsWith("PLASTIC");
	}
	
	public static Predicate<String, Order> isCheap() {
		return (key, order) -> Double.valueOf(order.getPrice()) < 300;
	}
	
	public static KeyValueMapper<String, Order, String> generateBase64Key() {
		return (key, order) -> Base64.getEncoder().encodeToString(String.valueOf(order.getOrderId()).getBytes());
	} 
}
