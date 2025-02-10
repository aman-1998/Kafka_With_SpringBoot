package personal.learning.consumer;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.retry.annotation.Backoff;

import personal.learning.dto.Customer;

public class MessageConsumer {
	
	@RetryableTopic(attempts = "4",
		    		backoff = @Backoff(delay = 2000, multiplier = 2), /* 2s, 4s, 8s */ 
		    		autoCreateTopics = "false",
		    		retryTopicSuffix = "-retry",
		    		dltTopicSuffix = "-dlt",
		    		topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
	@KafkaListener(id = "myListener", topics = {"TestTopic2", "TestTopic2-retry-0", "TestTopic2-retry-1", 
					"TestTopic2-retry-2"}, groupId = "group1", autoStartup = "false", 
					containerFactory = "kafkaListenerContainerFactory")
	public void consume(Customer message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) {
		System.out.println("====> Message received by cosumer: " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message.getId() == 111) {
				throw new RuntimeException("Invalid Id");
			}
		} catch(Exception ex) {
			System.out.println("An exception occurred :" + ex.getMessage());
			throw new RuntimeException("An exception occurred :" + ex.getMessage());
		}
		
	}
	
//	@RetryableTopic(attempts = "4")
//	@KafkaListener(id = "myListener2", topics = "TestTopic2", groupId = "group1", 
//				   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory",
//				   topicPartitions = {@TopicPartition(topic = "TestTopic2", partitions = {"1"})})
//	public void consume2(Customer message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
//			   @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
//			   @Header(name = KafkaHeaders.OFFSET, required = false) long offset) {
//		System.out.println("====> Message received by cosumer2: " + message);
//		System.out.println("====> Source topic : " + topic);
//		System.out.println("====> Source partition : " + partition);
//		System.out.println("====> Source offset : " + offset);
//		
//		if(message.getId() == 112) {
//			throw new RuntimeException("Invalid Id");
//		}
//	}
//	
//	@RetryableTopic(attempts = "4")
//	@KafkaListener(id = "myListener3", topics = "TestTopic2", groupId = "group1", 
//				   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory",
//				   topicPartitions = {@TopicPartition(topic = "TestTopic2", partitions = {"2"})})
//	public void consume3(Customer message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
//										   @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
//										   @Header(name = KafkaHeaders.OFFSET, required = false) long offset) {
//		System.out.println("====> Message received by cosumer3: " + message);
//		System.out.println("====> Source topic : " + topic);
//		System.out.println("====> Source partition : " + partition);
//		System.out.println("====> Source offset : " + offset);
//		
//		if(message.getId() == 113) {
//			throw new RuntimeException("Invalid Id");
//		}
//	}
	
//	@DltHandler
//	public void listenDLT(Customer message, @Headers Map<String, Object> kafkaMessageHeaders) {
//		System.out.println("------> Message received from DLT : " + message);
////		System.out.println("------> DLT topic name : " + topic);
////		System.out.println("------> DLT topic partition : " + partition);
////		System.out.println("------> DLT message offset : " + offset);
//		System.out.println("------> Kafka Message headers " + kafkaMessageHeaders);
//		
//	}
	
	@KafkaListener(topics = "TestTopic2-dlt", groupId = "group2", autoStartup = "true", 
			containerFactory = "kafkaListenerContainerFactory")
	public void listenDLT(ConsumerRecord<String, Customer> record) {
	    System.out.println("------> Message received from DLT : " + record.value());
	    System.out.println("------> Source Topic : " + record.topic());
	    System.out.println("------> Source Partition : " + record.partition());
	    System.out.println("------> Source Offset : " + record.offset());
	    System.out.println("------> Headers : " + record.headers());
	}

}
