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
	
	@Value("${test.topic.alpha}")
	private String topicAlpha;
	
	@Value("${test.topic.beta}")
	private String topicBeta;
	
	@Value("${test.topic.gamma}")
	private String topicGamma;
	
	@Value("${test.topic.delta}")
	private String topicDelta;
	
	@Value("${test.topic.epsilon}")
	private String topicEpsilon;
	
	@Value("${test.topic.zeta}")
	private String topicZeta;
	
	@Value("${test.topic.eta}")
	private String topicEta;
	
	@Value("${test.topic.theta}")
	private String topicTheta;
	
	@Bean
	public NewTopic topicAlpha() {
		return new NewTopic(topicAlpha, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicBeta() {
		return new NewTopic(topicBeta, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicGamma() {
		return new NewTopic(topicGamma, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicDelta() {
		return new NewTopic(topicDelta, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicEpsilon() {
		return new NewTopic(topicEpsilon, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicZeta() {
		return new NewTopic(topicZeta, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicEta() {
		return new NewTopic(topicEta, 2, (short)1);
	}
	
	@Bean
	public NewTopic topicTheta() {
		return new NewTopic(topicTheta, 2, (short)1);
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
