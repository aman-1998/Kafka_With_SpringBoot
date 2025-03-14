package personal.learning.consumer.global.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class CommonKafkaErrorHandler implements CommonErrorHandler {

	@Override
	public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer,
			MessageListenerContainer container, boolean batchListener) {
		
		System.out.println("######### Error occurred while processing "
				+ "the message " + thrownException.getMessage());
	}

	@Override
	public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer,
			MessageListenerContainer container) {
		
		System.out.println("######### Error occurred while processing the message "
				+ "received by CustomerConsumer : " + thrownException.getMessage());
		System.out.println("******** Sending message to Elasticsearch : " + record.value().toString());
		
		//true if the error was "handled" or false if not and the container will re-submit the record to the listener.
		return true;
	}
	
	
}
