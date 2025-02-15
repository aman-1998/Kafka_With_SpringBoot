package personal.learning.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import personal.learning.dto.Customer;

public class MessagePublisher {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessage(Customer message) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("TestTopic4", "TestKey", message);
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
