package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import personal.learning.dto.Customer;

/*
 * AckMode.MANUAL
 * ---------------
 * Behavior: When using AckMode.MANUAL, the offset is not committed immediately when 
 * Acknowledgment.acknowledge() is called. Instead, the commit is deferred and performed asynchronously 
 * by the Kafka consumer.
 * 
 * Use Case: This mode is useful when you want to batch multiple offset commits for better performance, 
 * especially in high-throughput systems.
 * 
 * Commit Timing: The actual commit happens when the Kafka consumer's internal logic decides to perform 
 * the commit (e.g., during the next poll cycle).
 */

public class MessageConsumer2 {
	
	@KafkaListener(id = "myListener2", topics = "${test.topic.name2}", groupId = "${test.group.name2}", 
				   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory2")
	public void consume(Customer message, Acknowledgment acknowledgment, 
										  @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by cosumer2: " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message.getId() == 111) {
				throw new RuntimeException("Invalid Id provided in consumer2");
			}
			
			TimeUnit.SECONDS.sleep(4); // Processing of message takes 4 secs
			
			/*
			 * Offset is not committed immediately.
			 * 
			 * Offset is committed asynchronously as per Kafka's internal mechanism/logic
			 * For eg., before next polling or before consumer shutdown, etc
			 */
			acknowledgment.acknowledge();
		} catch(Exception ex) {
			System.out.println("An exception occurred in consumer2:" + ex.getMessage());
			throw new RuntimeException("An exception occurred in consumer2:" + ex.getMessage());
		}
	}

}
