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
	
	@Value("${test.topic.name1}")
	private String topic1;
	
	@Value("${test.topic.name2}")
	private String topic2;
	
	@Value("${test.topic.name3}")
	private String topic3;
	
	@Bean
	public NewTopic topic1() {
		return new NewTopic(topic1, 3, (short)1);
	}
	
	@Bean
	public NewTopic topic2() {
		return new NewTopic(topic2, 3, (short)1);
	}
	
	@Bean
	public NewTopic topic3() {
		return new NewTopic(topic3, 3, (short)1);
	}
	
	public ProducerFactory<String, Object> producerFactoryAck0() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// acks=0: Do not wait for any acknowledgment
        props.put(ProducerConfig.ACKS_CONFIG, "0");
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	public ProducerFactory<String, Object> producerFactoryAck1() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// acks=1: Leader will acknowledge after writing locally
		// By default ack = 1
        props.put(ProducerConfig.ACKS_CONFIG, "1");
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	public ProducerFactory<String, Object> producerFactoryAckAll() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// acks=-1 or "all": Wait for all in-sync replicas
        props.put(ProducerConfig.ACKS_CONFIG, "all");
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplateAck0() {
		return new KafkaTemplate<>(producerFactoryAck0());
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplateAck1() {
		return new KafkaTemplate<>(producerFactoryAck1());
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplateAckAll() {
		return new KafkaTemplate<>(producerFactoryAckAll());
	}

}
