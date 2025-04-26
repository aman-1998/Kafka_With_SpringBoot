package personal.learning.publisher;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import personal.learning.dto.Customer;

public class MessagePublisher {
	
	@Value("${test.topic.purchase.mobile}")
	public String purchasedByMobileTopic;
	
	@Value("${test.topic.purchase.web}")
	public String purchasedByWebTopic;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void sendMessageCustomerPurchase(Customer message) {
		
		String targetTopic = StringUtils.EMPTY;
		String key = StringUtils.EMPTY;
		if(StringUtils.isNotBlank(message.getOperatingSys()) 
				&& StringUtils.isNotBlank(message.getGps())) {
			key = "mobile";
			targetTopic = purchasedByMobileTopic;
		} else {
			key = "web";
			targetTopic = purchasedByWebTopic;
		}
		
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(targetTopic, key, message);
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
