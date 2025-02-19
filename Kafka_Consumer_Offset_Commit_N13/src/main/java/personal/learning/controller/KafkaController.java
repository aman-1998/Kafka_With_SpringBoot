package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.dto.Customer;
import personal.learning.publisher.MessagePublisher;

@RestController
public class KafkaController {
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@PostMapping("/send1")
	public ResponseEntity<?> send1(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage1(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send2")
	public ResponseEntity<?> send2(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage2(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send3")
	public ResponseEntity<?> send3(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage3(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send4")
	public ResponseEntity<?> send4(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage4(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send5")
	public ResponseEntity<?> send5(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage5(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send6")
	public ResponseEntity<?> send6(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage6(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send7")
	public ResponseEntity<?> send7(@RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher.sendMessage7(customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
}
