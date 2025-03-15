package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import personal.learning.consumer.MessageConsumer;
import personal.learning.publisher.MessagePublisher;

@Configuration
public class AppConfig {
	
	@Bean
	public MessagePublisher MessagePublisher() {
		return new MessagePublisher();
	}
	
	@Bean
	public MessageConsumer messageConsumer() {
		return new MessageConsumer();
	}
	
}
