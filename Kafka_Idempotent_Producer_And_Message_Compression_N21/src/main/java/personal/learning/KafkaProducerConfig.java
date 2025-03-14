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
	
	@Value("${test.topic.customer.name}")
	private String customerTopic;
	
	@Bean
	public NewTopic customerTopic() {
		return new NewTopic(customerTopic, 3, (short)1);
	}
	
	@Value("${test.topic.food.order.name}")
	private String foodOrderTopic;
	
	@Bean
	public NewTopic foodOrderTopic() {
		return new NewTopic(foodOrderTopic, 3, (short)1);
	}
	
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	    
	    /*
	     * 
	     * 
	     * When a message is sent to a topic, the broker commits the offset and the ack is sent by the broker 
	     * back to the producer. If ack couldn't be received by the Producer (due to network error). 
	     * Then as part of retry mechanism, Producer sends the same message again to the topic. Now this time 
	     * the message will not be committed by the broker and only ack will be sent to the producer.
	     * 
	     * Kafka assigns a unique Producer ID (PID) and sequence numbers to each message to track duplicates.
		 * When enabled, Kafka guarantees exactly-once delivery within a session.
	     * 
	     * When idempotency is enabled, Kafka automatically sets the following configurations:
	     * ------------------------------------------------------------------------------------
	     * retries = 2147483647 (Integer.MAX_VALUE)
	     * max.in.flight.requests.per.connection = 5
	     * acks = all
	     * 
	     * 
	     */
	    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // Correct type (Boolean)
	    
	    /*
	     * Purpose: Enables message compression to reduce network bandwidth usage and improve throughput.
		 * Options:
		 * "none" → No compression (default)
		 * "gzip" → High compression, but slower
		 * "snappy" → Fast compression with moderate size reduction (recommended for performance)
		 * "lz4" → Faster than gzip, similar to snappy
		 * "zstd" → High compression, best for large messages
	     */
	    props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
	    
	    // Control batching size
	    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768);
	    
	    /*
	     * Controls how long Kafka waits before sending a batch of messages if 
	     * the size of messages has not reached full batch size.
	     * 
	     * If a batch reaches BATCH_SIZE_CONFIG, it is sent immediately. If not, Kafka 
	     * waits for more messages before sending (up to LINGER_MS_CONFIG)
	     */
	    props.put(ProducerConfig.LINGER_MS_CONFIG, 15000); // 15 secs

	    /*
	     * It is the maximum time before a message is considered failed
	     * If a message cannot be successfully acknowledged within this time, it fails permanently.
	     */
	    props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 30000); // 30 secs
	    
	    /*
	     * Time in milliseconds the producer waits for a response from the Kafka broker before retrying or failing.
	     * 
	     * If the broker does not respond within 10 seconds, the producer retries or fails.
		 * Works together with retries and delivery.timeout.ms to control failure handling.
	     */
	    props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 10000); // 10 seconds
	    
	    return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
}
