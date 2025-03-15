package personal.learning.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import personal.learning.dto.Customer;
import personal.learning.dto.FoodOrder;
import personal.learning.publisher.MessagePublisher;

@Component
public class MessageSenderScheduler {
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@Scheduled(fixedRate = 5000) // Send message every 2 sec
	public void sendMessageToCustomerTopic() {
		Customer customer = new Customer();
		customer.setId(1200);
		customer.setName("Aman Mishra");
		customer.setPhone("+91833283838");
		customer.setEmail("mishra.babu@gmail.com");
		
		System.out.println("Customer --> " + customer);
		messagePublisher.sendMessageCustomer(customer);
	}
	
	@Scheduled(fixedRate = 5000) // Send message every 2 sec
	public void sendMessageToFoodOrderTopic() {
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setId(700);
		foodOrder.setName("White Sauce Pasta");
		foodOrder.setRate("Rs. 120");
		foodOrder.setResturantName("Pista House");
		
		System.out.println("FoodOrder ==> " + foodOrder);
		messagePublisher.sendMessageFoodOrder(foodOrder);
	}
}
