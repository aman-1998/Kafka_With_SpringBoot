package personal.learning.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import personal.learning.dto.OrderSummary;

public class OrderSummaryConsumer {
	
	@KafkaListener(id = "myListener1", topics = "${test.topic.order.summary.plastic}", groupId = "${test.group.order}", 
			   	   autoStartup = "true", containerFactory = "kafkaListenerContainerFactory")
	public void consumePlastic(OrderSummary message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) 
										  throws Exception {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by OrderSummaryConsumer >> consumePlastic: " + message);
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
			System.out.println("An exception occurred in OrderSummaryConsumer >> consumePlastic : " + ex.getMessage());
			throw new RuntimeException("An exception occurred in OrderSummaryConsumer : " + ex.getMessage());
		}
	}
	
	@KafkaListener(id = "myListener2", topics = "${test.topic.order.summary.non.plastic}", groupId = "${test.group.order}", 
		   	   autoStartup = "true", containerFactory = "kafkaListenerContainerFactory")
	public void consumeNonPlastic(OrderSummary message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) 
										  throws Exception {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by OrderSummaryConsumer >> consumeNonPlastic: " + message);
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
			System.out.println("An exception occurred in OrderSummaryConsumer >> consumeNonPlastic: " + ex.getMessage());
			throw new RuntimeException("An exception occurred in OrderSummaryConsumer >> consumeNonPlastic: " + ex.getMessage());
		}
	}

}
