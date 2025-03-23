package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import personal.learning.dto.Order;

public class MaskedCreditCardConsumer {
	
	@KafkaListener(id = "myListener1", topics = "${test.topic.order.masked.creditcard}", groupId = "${test.group.order}", 
			   	   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory")
	public void consume(Order message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) 
										  throws Exception {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by MaskedCreditCardConsumer: " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message.getOrderId() == 111) {
				throw new RuntimeException("Invalid promotion code provided");
			}
			
			TimeUnit.SECONDS.sleep(3); // Processing of message takes 4 secs
			
			// Spring Kafka will commit automatically after each message is processed successfully.
			
			System.out.println("=========Processing done=========");
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in MaskedCreditCardConsumer : " + ex.getMessage());
			throw new RuntimeException("An exception occurred in MaskedCreditCardConsumer : " + ex.getMessage());
		}
	}

}
