package personal.learning.consumer.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service(value = "customerErrorHandler")
public class CustomerErrorHandler implements ConsumerAwareListenerErrorHandler {

	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
		System.out.println(":::::: Error occurred while processing the message "
					+ "received by CustomerConsumer : " + exception.getMessage());
		
		if(exception.getCause() instanceof RuntimeException) {
			throw exception;
		}
		
		return null;
	}

}
