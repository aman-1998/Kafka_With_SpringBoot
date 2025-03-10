package personal.learning;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
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

import personal.learning.dto.Customer;
import personal.learning.dto.FoodOrder;

@Configuration
public class KafkaConsumerConfig {
	
	@Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // Disable auto-commit
        
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "personal.learning.dto");
        
        /* Explicitly set the target type for deserialization
         * If we don't specify target class explicitly then deserialization will be done by Kafka
         * based on the method signature of consume method annotated with @KafkaListener.
         */
        //return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), 
        //            new JsonDeserializer<>(Customer.class));
        
        return new DefaultKafkaConsumerFactory<>(props);
    }
    
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory(
    		ConsumerFactory<String, Object> consumerFactory, KafkaTemplate<String, Object> kafkaTemplate) {
        
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        //Error handler to handle retries and send failed messages to DLT
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate, 
        		(record, ex) -> {
        			if(record.value() instanceof Customer) {
        				return new TopicPartition("t-customer-DLT", record.partition());
        			} else if(record.value() instanceof FoodOrder) {
        				return new TopicPartition("t-foodOrder-DLT", record.partition());
        			}
        			return null;
        		});
        
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, 
                new FixedBackOff(2000, 3) // 3 retry attempts with a 2-second delay
                );

        factory.setCommonErrorHandler(errorHandler);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        
        factory.setRecordFilterStrategy(consumerRecord -> {
        	if(consumerRecord.value() instanceof Customer) {
        		Customer customer = (Customer) consumerRecord.value();
            	if(customer.getId() < 1000) {
            		return true;
            	} 
            	return false;
        	} else if(consumerRecord.value() instanceof FoodOrder) {
        		FoodOrder foodOrder = (FoodOrder) consumerRecord.value();
        		if(foodOrder.getId() == 786) {
            		return true;
            	}
        		return false;
        	} 
        	return false; // Keep the record if it's not a Customer or FoodOrder
        });
        
        return factory;
    }
    
}
