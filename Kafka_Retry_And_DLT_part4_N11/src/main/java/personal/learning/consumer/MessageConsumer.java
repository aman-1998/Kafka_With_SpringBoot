package personal.learning.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;

import personal.learning.dto.Customer;
/*
 * autoStartup = "true" is very important here as it ensures retry and DLT topics and groups are created.
 * 
 * If autoStartup = "false" then even after manually starting listener, the groups will be created only 
 * for those topics which are mentioned in @KafkaListener and not for retry & DLT topics.
 * 
 * autoStartup = "" then before stopping the listener if we get at least one message in retry topic then groups
 * for retry & DLT topic will be created. But if we stop the listener before getting a message in retry topic
 * then even after manually starting listener, the groups will be created only for those topics which are 
 * mentioned in @KafkaListener and not for retry & DLT topics. 
 */

/*
 * When an exception occurred then the if message went to partition 1 of main topic then that particular
 * message is routed to same partition of retry topic. So, when partition count of retry topic does not 
 * match with partition count of main topic then Kafka won't be able to route the message to the same
 * partition of retry topic. And then Kafka will dynamically determine the partition for retry topic.
 * Kafka's default partitioner ensures that messages are routed to valid partitions, even if the 
 * partition counts do not match.
 * 
 * So, ultimately retry mechanism will work but efficiency will be reduced because of uneven load distribution.
 *
 * So, partition count of retry topic should match with main topic's partition count. The same concept 
 * is applicable for replicas as well.
 */

/*
 * Note:
 * -----
 * idIsGroup=false => When groupId is not provided, use the id (if provided) as the group.id property 
 * for the consumer. Set to false, to use the group.id from the consumer factory. 
 * If groupId is not provided in @KafkaListener then the group provided in consumer-factory will be 
 * the group for main as well as retry and DLT topics. 
 */
public class MessageConsumer {
	
	@RetryableTopic(attempts = "4",
		    		backoff = @Backoff(delay = 2000, multiplier = 2), /* 2s, 4s, 8s */ 
		    		autoCreateTopics = "true",
		    		retryTopicSuffix = "-retry", dltTopicSuffix = "-dlt",
		    		numPartitions = "3", replicationFactor = "1",
		    		topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
	@KafkaListener(id = "myListener", topics = "TestTopic6", autoStartup = "true", 
				   containerFactory = "kafkaListenerContainerFactory", idIsGroup = false)
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
	
	@DltHandler
	public void listenDLT(ConsumerRecord<String, Customer> record) {
	    System.out.println("------> Message received from DLT : " + record.value());
	    System.out.println("------> Source Topic : " + record.topic());
	    System.out.println("------> Source Partition : " + record.partition());
	    System.out.println("------> Source Offset : " + record.offset());
	    System.out.println("------> Headers : " + record.headers());
	}

}
