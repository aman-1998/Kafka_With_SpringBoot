package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import personal.learning.consumer.MessageConsumer1;
import personal.learning.consumer.MessageConsumer2;
import personal.learning.consumer.MessageConsumer3;
import personal.learning.consumer.MessageConsumer4;
import personal.learning.consumer.MessageConsumer5;
import personal.learning.consumer.MessageConsumer6;
import personal.learning.consumer.MessageConsumer7;
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
	
	@Bean
	public MessageConsumer4 messageConsumer4() {
		return new MessageConsumer4();
	}
	
	@Bean
	public MessageConsumer5 messageConsumer5() {
		return new MessageConsumer5();
	}
	
	@Bean
	public MessageConsumer6 messageConsumer6() {
		return new MessageConsumer6();
	}
	
	@Bean
	public MessageConsumer7 messageConsumer7() {
		return new MessageConsumer7();
	}
	
}
