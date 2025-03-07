package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.dto.Customer;
import personal.learning.publisher.MessagePublisher1;
import personal.learning.publisher.MessagePublisher2;
import personal.learning.publisher.MessagePublisher3;
import personal.learning.publisher.MessagePublisher4;

@RestController
public class KafkaController {
	
	@Autowired
	private MessagePublisher1 messagePublisher1;
	
	@Autowired
	private MessagePublisher2 messagePublisher2;
	
	@Autowired
	private MessagePublisher3 messagePublisher3;
	
	@Autowired
	private MessagePublisher4 messagePublisher4;
	
	@PostMapping("/send1/{partition}")
	public ResponseEntity<?> send1(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher1.sendMessageAlpha(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send2/{partition}")
	public ResponseEntity<?> send2(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher1.sendMessageBeta(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send3/{partition}")
	public ResponseEntity<?> send3(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher2.sendMessageGama(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send4/{partition}")
	public ResponseEntity<?> send4(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher2.sendMessageDelta(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send5/{partition}")
	public ResponseEntity<?> send5(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher3.sendMessageEpsilon(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send6/{partition}")
	public ResponseEntity<?> send6(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher3.sendMessageZeta(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send7/{partition}")
	public ResponseEntity<?> send7(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher4.sendMessageEta(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send8/{partition}")
	public ResponseEntity<?> send8(@PathVariable("partition") String partition, @RequestBody Customer customer) {
		try {
			System.out.println("Customer -> " + customer);
			messagePublisher4.sendMessageTheta(partition, customer);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
}
