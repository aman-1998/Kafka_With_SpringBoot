package personal.learning.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import personal.learning.dto.CustomerShopingCart;
import personal.learning.dto.CustomerWishlist;

public class MessagePublisher {
	
	@Value("${test.topic.shopping.cart}")
	public String shoppingCartTopic;
	
	@Value("${test.topic.wishlist}")
	public String wishListTopic;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendShoppingCartMessage(CustomerShopingCart message) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(shoppingCartTopic, message.getLocation(), message);
		future.whenComplete((result, ex) -> {
			if(ex == null) {
				System.out.println("~~~~~~> Sent message: " + message);
				System.out.println("~~~~~~> Destination topic : " + result.getRecordMetadata().topic());
				System.out.println("~~~~~~> Destination partition : " + result.getRecordMetadata().partition());
				System.out.println("~~~~~~> Source offset : " + result.getRecordMetadata().offset());
				System.out.println("Timestamp : " + result.getRecordMetadata().timestamp());
				System.out.println("--------------------------------------------------------------------------");
			} else {
				System.out.println("Failed to send message["+message+"] due to : " + ex.getMessage());
			}
		});
	}
	
	public void sendWishlistMessage(CustomerWishlist message) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(shoppingCartTopic, message.getLocation(), message);
		future.whenComplete((result, ex) -> {
			if(ex == null) {
				System.out.println("~~~~~~> Sent message: " + message);
				System.out.println("~~~~~~> Destination topic : " + result.getRecordMetadata().topic());
				System.out.println("~~~~~~> Destination partition : " + result.getRecordMetadata().partition());
				System.out.println("~~~~~~> Source offset : " + result.getRecordMetadata().offset());
				System.out.println("Timestamp : " + result.getRecordMetadata().timestamp());
				System.out.println("--------------------------------------------------------------------------");
			} else {
				System.out.println("Failed to send message["+message+"] due to : " + ex.getMessage());
			}
		});
	}
	
}
