package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.dto.Order;
import personal.learning.publisher.MessagePublisher;

@RestController
public class KafkaController {
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@PostMapping("/send")
	public ResponseEntity<?> send(@RequestBody Order message) {
		try {
			System.out.println("Order -> " + message);
			messagePublisher.sendMessageOrder(message);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			System.out.println("Exception occurred due to " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
}
