package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

public class FraudAnalysisConsumer {
	
	@KafkaListener(id = "myListener5", topics = "${test.topic.fraud.analysis}", groupId = "${test.group.order}", 
			   	   autoStartup = "true", containerFactory = "kafkaListenerContainerFactory")
	public void consume(Integer message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) 
										  throws Exception {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by FraudAnalysisConsumer: " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message > 10000000) {
				throw new RuntimeException("Error in amount calculation");
			}
			
			TimeUnit.SECONDS.sleep(3); // Processing of message takes 4 secs
			
			// Spring Kafka will commit automatically after each message is processed successfully.
			
			System.out.println("=========Processing done=========");
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in FraudAnalysisConsumer : " + ex.getMessage());
			throw new RuntimeException("An exception occurred in FraudAnalysisConsumer : " + ex.getMessage());
		}
	}

}
