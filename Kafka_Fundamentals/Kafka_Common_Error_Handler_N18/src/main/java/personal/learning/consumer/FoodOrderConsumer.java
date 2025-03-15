package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import personal.learning.dto.FoodOrder;

public class FoodOrderConsumer {
	
	@KafkaListener(id = "myListener2", topics = "${test.topic.food.order.name}", groupId = "${test.group.name}", 
		   	   	   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory")
	public void consume(FoodOrder message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										   @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										   @Header(name = KafkaHeaders.OFFSET, required = false) long offset) 
										   throws Exception {
		
		System.out.println("====> Message received by FoodOrderConsumer : " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message.getId() == 500) {
				throw new RuntimeException("Invalid Id provided in FoodOrderConsumer");
			}
			
			TimeUnit.SECONDS.sleep(3); // Processing of message takes 4 secs
			
			// Spring Kafka will commit automatically after each message is processed successfully.
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in FoodOrderConsumer : " + ex.getMessage());
			throw new RuntimeException("An exception occurred in FoodOrderConsumer : " + ex.getMessage());
		}
	}
}
