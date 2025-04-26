package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.consumer.NegativeWordsConsumer;
import personal.learning.consumer.NegativeWordsCountConsumer;
import personal.learning.consumer.OverallBadWordCountConsumer;
import personal.learning.consumer.OverallGoodWordCountConsumer;
import personal.learning.consumer.PositiveWordsConsumer;
import personal.learning.consumer.PositiveWordsCountConsumer;
import personal.learning.publisher.MessagePublisher;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public MessagePublisher MessagePublisher() {
		return new MessagePublisher();
	}
	
	@Bean
	public PositiveWordsConsumer positiveWordsConsumer() {
		return new PositiveWordsConsumer();
	}
	
	@Bean
	public NegativeWordsConsumer negativeWordsConsumer() {
		return new NegativeWordsConsumer();
	}
	
	@Bean
	public NegativeWordsCountConsumer negativeWordsCountConsumer() {
		return new NegativeWordsCountConsumer();
	}
	
	@Bean
	public PositiveWordsCountConsumer positiveWordsCountConsumer() {
		return new PositiveWordsCountConsumer();
	}
	
	@Bean
	public OverallBadWordCountConsumer overallBadWordCountConsumer() {
		return new OverallBadWordCountConsumer();
	}
	
	@Bean
	public OverallGoodWordCountConsumer overallGoodWordCountConsumer() {
		return new OverallGoodWordCountConsumer();
	}
	
}
