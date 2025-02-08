package personal.learning.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import personal.learning.dto.Customer;

public class MessagePublisher {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessage(String partition, Customer message) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("TestTopic1", 
																				  Integer.valueOf(partition), 
																				  null, message);
		future.whenComplete((result, ex) -> {
			if(ex == null) {
				System.out.println("Sent message [" + message + "] to topic [" 
									+ result.getRecordMetadata().topic() + "]");
				System.out.println("Partition : " + result.getRecordMetadata().partition());
				System.out.println("Offset : " + result.getRecordMetadata().offset());
				System.out.println("Timestamp : " + result.getRecordMetadata().timestamp());
			} else {
				System.out.println("Failed to send message["+message+"] due to : " + ex.getMessage());
			}
		});
	}

	
	
}
