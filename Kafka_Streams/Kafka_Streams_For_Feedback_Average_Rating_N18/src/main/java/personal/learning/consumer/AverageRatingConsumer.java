package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import personal.learning.dto.AvgFeedbackRating;

public class AverageRatingConsumer {
	
	@KafkaListener(id = "myListener1", topics = "${test.topic.average.rating}", groupId = "${test.group.feedback}", 
		   	   autoStartup = "true", containerFactory = "kafkaListenerContainerFactory1")
	public void consume(AvgFeedbackRating message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
									  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
		 							  @Header(name = KafkaHeaders.OFFSET, required = false) long offset,
									  @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key) 
									  throws Exception {
									  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by AverageRatingConsumer: key="+ key + ", value=" + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			TimeUnit.SECONDS.sleep(3); // Processing of message takes 4 secs
			
			// Spring Kafka will commit automatically after each message is processed successfully.
			
			System.out.println("=========Processing done=========");
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in AverageRatingConsumer : " + ex.getMessage());
			throw new RuntimeException("An exception occurred in AverageRatingConsumer : " + ex.getMessage());
		}
	}
}
