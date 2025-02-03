package personal.learning.consumer;

import org.springframework.kafka.annotation.KafkaListener;

import personal.learning.dto.Customer;

public class MessageConsumer {
	
	@KafkaListener(id = "myListener1", topics = "TestTopic1", groupId = "group1", autoStartup = "false")
	public void consume1(Customer message) {
		System.out.println("Message received by cosumer1: " + message);
	}
	
	@KafkaListener(id = "myListener2", topics = "TestTopic1", groupId = "group1", autoStartup = "false")
	public void consume2(Customer message) {
		System.out.println("Message received by consumer2: " + message);
	}
	
	@KafkaListener(id = "myListener3", topics = "TestTopic1", groupId = "group1", autoStartup = "false")
	public void consume3(Customer message) {
		System.out.println("Message received by consumer3: " + message);
	}

}
