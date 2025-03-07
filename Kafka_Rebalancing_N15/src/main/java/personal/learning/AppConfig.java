package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.publisher.MessagePublisher1;
import personal.learning.publisher.MessagePublisher2;
import personal.learning.publisher.MessagePublisher3;
import personal.learning.publisher.MessagePublisher4;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public MessagePublisher1 MessagePublisher1() {
		return new MessagePublisher1();
	}
	
	@Bean
	public MessagePublisher2 MessagePublisher2() {
		return new MessagePublisher2();
	}
	
	@Bean
	public MessagePublisher3 MessagePublisher3() {
		return new MessagePublisher3();
	}
	
	@Bean
	public MessagePublisher4 MessagePublisher4() {
		return new MessagePublisher4();
	}
	
}
