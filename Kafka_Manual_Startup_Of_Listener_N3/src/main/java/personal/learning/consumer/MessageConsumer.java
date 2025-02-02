package personal.learning.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageConsumer {
	
	@KafkaListener(id = "myListener1", topics = "TestTopic1", groupId = "group1", autoStartup = "false")
	public void consume(String message) {
		System.out.println("Message received : " + message);
	}

}
