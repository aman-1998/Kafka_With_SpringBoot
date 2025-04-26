package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import personal.learning.dto.CustomerAggregate;

public class CustomerAggregateConsumer {
	
	@Value("${test.topic.aggregate}")
	public String aggregateTopic;
	
	@KafkaListener(id = "myListener1", topics = "${test.topic.aggregate}", groupId = "${test.group.aggregate}", 
		   	   autoStartup = "true", containerFactory = "kafkaListenerContainerFactory1")
	public void consume(CustomerAggregate message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
									  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
									  @Header(name = KafkaHeaders.OFFSET, required = false) long offset,
									  @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key) 
									  throws Exception {
									  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by CustomerAggregateConsumer: key="+ key + ", value=" + message.toString());
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			TimeUnit.SECONDS.sleep(3); // Processing of message takes 4 secs
			
			// Spring Kafka will commit automatically after each message is processed successfully.
			
			System.out.println("=========Processing done=========");
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in CustomerAggregateConsumer : " + ex.getMessage());
			throw new RuntimeException("An exception occurred in CustomerAggregateConsumer : " + ex.getMessage());
		}
	}
}
