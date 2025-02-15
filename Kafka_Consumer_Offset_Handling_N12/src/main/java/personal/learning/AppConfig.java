package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import personal.learning.consumer.MessageConsumer1;
import personal.learning.consumer.MessageConsumer2;
import personal.learning.consumer.MessageConsumer3;
import personal.learning.publisher.MessagePublisher;

@Configuration
public class AppConfig {
	
	@Bean
	public MessagePublisher MessagePublisher() {
		return new MessagePublisher();
	}
	
	@Bean
	public MessageConsumer1 messageConsumer1() {
		return new MessageConsumer1();
	}
	
	@Bean
	public MessageConsumer2 messageConsumer2() {
		return new MessageConsumer2();
	}
	
	@Bean
	public MessageConsumer3 messageConsumer3() {
		return new MessageConsumer3();
	}
	
}
