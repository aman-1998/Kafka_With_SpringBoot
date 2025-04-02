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
	
	@Value("${test.topic.order}")
	public String orderTopic;
	
	@Value("${test.topic.order.summary.plastic}")
	public String orderSummaryForPlasticTopic;
	
	@Value("${test.topic.order.summary.non.plastic}")
	public String orderSummaryForNonPlasticTopic;
	
	@Value("${test.topic.order.reward}")
	public String orderRewardTopic;
	
	@Value("${test.topic.order.storage}")
	public String orderStorageTopic;
	
	@Value("${test.topic.fraud.analysis}")
	public String fraudAnalysisTopic;
	
	@Bean
	public NewTopic orderTopic() {
		return new NewTopic(orderTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic orderSummaryForPlasticTopic() {
		return new NewTopic(orderSummaryForPlasticTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic orderSummaryForNonPlasticTopic() {
		return new NewTopic(orderSummaryForNonPlasticTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic orderRewardTopic() {
		return new NewTopic(orderRewardTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic orderStorageTopic() {
		return new NewTopic(orderStorageTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic fraudAnalysisTopic() {
		return new NewTopic(fraudAnalysisTopic, 3, (short)1);
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
