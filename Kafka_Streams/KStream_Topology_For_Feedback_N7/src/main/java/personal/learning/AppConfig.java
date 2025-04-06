package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.consumer.GoodFeedbackConsumer;
import personal.learning.consumer.PositiveWordsConsumer;
import personal.learning.publisher.MessagePublisher;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public MessagePublisher MessagePublisher() {
		return new MessagePublisher();
	}
	
	@Bean
	public GoodFeedbackConsumer goodFeedbackConsumer() {
		return new GoodFeedbackConsumer();
	}
	
	@Bean
	public PositiveWordsConsumer positiveWordsConsumer() {
		return new PositiveWordsConsumer();
	}
	
}
