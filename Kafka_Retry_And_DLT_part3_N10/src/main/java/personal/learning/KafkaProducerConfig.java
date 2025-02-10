package personal.learning;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {
	
	@Bean
	public NewTopic topic() {
		return new NewTopic("TestTopic2", 3, (short)1);
	}
	
	@Bean
	public NewTopic retryTopic0() {
		return new NewTopic("TestTopic2-retry-0", 3, (short)1);
	}
	
	@Bean
	public NewTopic retryTopic1() {
		return new NewTopic("TestTopic2-retry-1", 3, (short)1);
	}
	
	@Bean
	public NewTopic retryTopic2() {
		return new NewTopic("TestTopic2-retry-2", 3, (short)1);
	}
	
	@Bean
	public NewTopic dltTopic() {
		return new NewTopic("TestTopic2-dlt", 3, (short)1);
	}
	
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
