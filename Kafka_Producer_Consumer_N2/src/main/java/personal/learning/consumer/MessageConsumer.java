package personal.learning.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageConsumer {
	
	@KafkaListener(topics = "TestTopic1", groupId = "group1")
	public void consume(String message) {
		System.out.println("Message received : " + message);
	}

}
