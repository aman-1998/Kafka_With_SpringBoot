package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.dto.CustomerShopingCart;
import personal.learning.dto.CustomerWishlist;
import personal.learning.publisher.MessagePublisher;

@RestController
public class KafkaController {
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@PostMapping("/send/shoppingcart")
	public ResponseEntity<?> send(@RequestBody CustomerShopingCart message) {
		try {
			System.out.println("CustomerShopingCart -> " + message);
			messagePublisher.sendShoppingCartMessage(message);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			System.out.println("Exception occurred due to " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send/wishlist")
	public ResponseEntity<?> send(@RequestBody CustomerWishlist message) {
		try {
			System.out.println("CustomerWishlist -> " + message);
			messagePublisher.sendWishlistMessage(message);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			System.out.println("Exception occurred due to " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
}
