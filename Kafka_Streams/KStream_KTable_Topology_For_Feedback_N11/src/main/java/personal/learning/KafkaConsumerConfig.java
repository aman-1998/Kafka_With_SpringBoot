package personal.learning;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {
	
	@Bean
    public ConsumerFactory<String, Object> consumerFactory1() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // Disable auto-commit
        
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "personal.learning.dto");
        
        return new DefaultKafkaConsumerFactory<>(props);
    }
	
	@Bean
    public ConsumerFactory<String, Object> consumerFactory2() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // Disable auto-commit
        
        return new DefaultKafkaConsumerFactory<>(props);
    }
	
	@Bean
    public ConsumerFactory<String, Object> consumerFactory3() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // Disable auto-commit
        
        return new DefaultKafkaConsumerFactory<>(props);
    }
    
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory1(
    		ConsumerFactory<String, Object> consumerFactory1, KafkaTemplate<String, Object> kafkaTemplate) {
        
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory1);

         // Error handler to handle retries and send failed messages to DLT
         DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                 new DeadLetterPublishingRecoverer(kafkaTemplate), 
                 new FixedBackOff(2000, 3) // 3 retry attempts with a 2-second delay
         );

         factory.setCommonErrorHandler(errorHandler);
         factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        
        return factory;
    }
    
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory2(
    		ConsumerFactory<String, Object> consumerFactory2, KafkaTemplate<String, Object> kafkaTemplate) {
        
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory2);

         // Error handler to handle retries and send failed messages to DLT
         DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                 new DeadLetterPublishingRecoverer(kafkaTemplate), 
                 new FixedBackOff(2000, 3) // 3 retry attempts with a 2-second delay
         );

         factory.setCommonErrorHandler(errorHandler);
         factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        
        return factory;
    }
    
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory3(
    		ConsumerFactory<String, Object> consumerFactory3, KafkaTemplate<String, Object> kafkaTemplate) {
        
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory3);

         // Error handler to handle retries and send failed messages to DLT
         DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                 new DeadLetterPublishingRecoverer(kafkaTemplate), 
                 new FixedBackOff(2000, 3) // 3 retry attempts with a 2-second delay
         );

         factory.setCommonErrorHandler(errorHandler);
         factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        
        return factory;
    }
    
}
