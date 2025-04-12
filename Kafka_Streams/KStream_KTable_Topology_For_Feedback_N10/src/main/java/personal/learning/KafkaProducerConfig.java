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
	
	@Value("${test.topic.feedback}")
	public String feedbackTopic;
	
	@Value("${test.topic.positive.word}")
	public String positiveWordTopic;
	
	@Value("${test.topic.negative.word}")
	public String negativeWordTopic;
	
	@Value("${test.topic.positive.word.count}")
	public String positiveWordCountTopic;
	
	@Value("${test.topic.negative.word.count}")
	public String negativeWordCountTopic;
	
	@Value("${test.topic.overall.good.word.count}")
	public String overallGoodWordCountTopic;
	
	@Value("${test.topic.overall.bad.word.count}")
	public String overallBadWordCountTopic;
	
	@Bean
	public NewTopic feedbackTopic() {
		return new NewTopic(feedbackTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic positiveWordTopic() {
		return new NewTopic(positiveWordTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic negativeWordTopic() {
		return new NewTopic(negativeWordTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic positiveWordCountTopic() {
		return new NewTopic(positiveWordCountTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic negativeWordCountTopic() {
		return new NewTopic(negativeWordCountTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic overallGoodWordCountTopic() {
		return new NewTopic(overallGoodWordCountTopic, 3, (short)1);
	}
	
	@Bean
	public NewTopic overallBadWordCountTopic() {
		return new NewTopic(overallBadWordCountTopic, 3, (short)1);
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
