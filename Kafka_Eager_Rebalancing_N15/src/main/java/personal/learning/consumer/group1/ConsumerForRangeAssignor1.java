package personal.learning.consumer.group1;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import personal.learning.dto.Customer;

@Component
public class ConsumerForRangeAssignor1 {
	
	@KafkaListener(id = "myListener1", topics = {"${test.topic.alpha}", "${test.topic.beta}"}, groupId = "${test.group.name1}", 
			   	   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory1")
	public void consume(Customer message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by consumer1: " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message.getId() == 111) {
				throw new RuntimeException("Invalid Id provided in consumer1");
			}
			
			TimeUnit.SECONDS.sleep(3); // Processing of message takes 4 secs
			
			// Spring Kafka will commit automatically after each message is processed successfully.
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in consumer1:" + ex.getMessage());
			throw new RuntimeException("An exception occurred in consumer1:" + ex.getMessage());
		}
	}

}
