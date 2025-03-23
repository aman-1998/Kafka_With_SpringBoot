package personal.learning.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import personal.learning.dto.Customer;

public class MessagePublisher {
	
	@Value("${test.topic.customer.name}")
	private String customerTopic;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessageCustomer(String customerMsg) {
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(customerTopic, customerMsg);
		future.whenComplete((result, ex) -> {
			if(ex == null) {
				System.out.println("~~~~~~> Sent message: " + customerMsg);
				System.out.println("~~~~~~> Destination topic : " + result.getRecordMetadata().topic());
				System.out.println("~~~~~~> Destination partition : " + result.getRecordMetadata().partition());
				System.out.println("~~~~~~> Source offset : " + result.getRecordMetadata().offset());
				System.out.println("Timestamp : " + result.getRecordMetadata().timestamp());
				System.out.println("--------------------------------------------------------------------------");
			} else {
				System.out.println("Failed to send message["+customerMsg+"] due to : " + ex.getMessage());
			}
		});
	}
	
}
