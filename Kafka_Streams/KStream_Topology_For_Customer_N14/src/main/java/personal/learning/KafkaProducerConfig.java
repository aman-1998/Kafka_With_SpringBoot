package personal.learning;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {
	
	@Value("${test.topic.shopping.cart}")
	public String shoppingCartTopic;
	
	@Value("${test.topic.wishlist}")
	public String wishListTopic;
	
	@Value("${test.topic.aggregate}")
	public String aggregateTopic;
	
	@Bean
	public NewTopic shoppingCartTopic() {
		return new NewTopic(shoppingCartTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic wishListTopic() {
		return new NewTopic(wishListTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic aggregateTopic() {
		return new NewTopic(aggregateTopic, 3, (short)1);
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
