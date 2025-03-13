package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * Steps to test the application
 * ------------------------------
 * 
 * 1. Start the Application.
 * 
 * 2. Start both listeners manually using REST API.
 * 
 * 3. The message will be sent every 5 secs to both the topics and 
 *    both the consumers will receive message.
 *    
 * 4. At 1:25 PM FoodOrderConsumer will stop and hence no message 
 * 	  will be received by the the consumer.
 * 
 * 5. At 1:30 PM FoodOrderConsumer will be started again and it 
 * will start receiving messages as usual.
 * 
 * 6. During this entire time CustomerConsumer will continue 
 * 	  receiving messages.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.dto.Customer;
import personal.learning.dto.FoodOrder;
import personal.learning.publisher.MessagePublisher;

@RestController
public class KafkaController {
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@PostMapping("/send1")
	public ResponseEntity<?> send(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessageCustomer(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send2")
	public ResponseEntity<?> send(@RequestBody FoodOrder foodOrder) {
		try {
			System.out.println("FoodOrder -> " + foodOrder);
			messagePublisher.sendMessageFoodOrder(foodOrder);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
}
