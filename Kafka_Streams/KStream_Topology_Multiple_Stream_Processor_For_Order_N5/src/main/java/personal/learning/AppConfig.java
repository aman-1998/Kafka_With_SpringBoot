package personal.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import personal.learning.consumer.OrderRewardConsumer;
import personal.learning.consumer.OrderStorageConsumer;
import personal.learning.consumer.OrderSummaryConsumer;
import personal.learning.publisher.MessagePublisher;

@Configuration
@ComponentScan(basePackages = {"personal.learning"})
public class AppConfig {
	
	@Bean
	public MessagePublisher MessagePublisher() {
		return new MessagePublisher();
	}
	
	@Bean
	public OrderRewardConsumer orderRewardConsumer() {
		return new OrderRewardConsumer();
	}
	
	@Bean
	public OrderStorageConsumer orderStorageConsumer() {
		return new OrderStorageConsumer();
	}
	
	@Bean
	public OrderSummaryConsumer orderSummaryConsumer() {
		return new OrderSummaryConsumer();
	}
	
}
