package personal.learning.consumer;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

import personal.learning.dto.Customer;

/*
 * auto.offset.reset is only applied if there are no committed offsets for the group.
 */

/*
 * autoStartup = "false" ==> Because we want to test auto-offset-reset latest/earliest/none behavior.
 * And also retry and DLT related code is not there because we want autoStartup = "false"
 */

/*
 * auto.offset.reset = latest ==> If there are no committed offsets, then consumer will skip messages 
 * which are already produced in the topic before consumer starts. The consumer will consume only 
 * those message which arrive after the consumer started. The consumption of message will be in FIFO order.
 * 
 * auto.offset.reset = earliest ==> If there are no committed offsets, then consumer will consume messages 
 * which are already produced in the topic before consumer starts. The consumer will also consume 
 * those message which arrive after the consumer started. The consumption of message will be in FIFO order.
 * 
 * auto.offset.reset = none ==> If there are no committed offsets, the consumer will fail immediately with 
 * an error instead of consuming from the beginning or the latest position.
 * 
 * 
 * Note:
 * -----
 * If there is at least one committed offset then auto.offset.reset is not applicable. In that case messages 
 * will be consumed in FIFO order starting from last committed offset.
 *
 */

public class MessageConsumer1 {

	@KafkaListener(id = "myListener1", topics = "${test.topic.name1}", groupId = "${test.group.name1}", 
				   autoStartup = "false", containerFactory = "kafkaListenerContainerFactory1")
	public void consume(Customer message, @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topic, 
										  @Header(name = KafkaHeaders.RECEIVED_PARTITION, required = false) int partition,
										  @Header(name = KafkaHeaders.OFFSET, required = false) long offset) {
										  // @Headers Map<String, Object> header) {
		System.out.println("====> Message received by cosumer1: " + message);
		System.out.println("====> Source topic : " + topic);
		System.out.println("====> Source partition : " + partition);
		System.out.println("====> Source offset : " + offset);
		try {
			if(message.getId() == 111) {
				throw new RuntimeException("Invalid Id provided in consumer1");
			}
			
			TimeUnit.SECONDS.sleep(20); // Processing of message takes
			
		} catch(Exception ex) {
			System.out.println("An exception occurred in consumer1:" + ex.getMessage());
			throw new RuntimeException("An exception occurred in consumer1:" + ex.getMessage());
		}
	}

}
