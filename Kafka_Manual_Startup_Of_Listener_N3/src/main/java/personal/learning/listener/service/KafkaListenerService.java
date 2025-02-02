package personal.learning.listener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {
	
	@Autowired
	private KafkaListenerEndpointRegistry registry;
	
	public void startListener(String listenerId) {
		MessageListenerContainer container = registry.getListenerContainer(listenerId);
		if(container != null) {
			if(!container.isRunning()) {
				container.start();
				System.out.println(listenerId + " started...");
			} else  {
				System.out.println(listenerId + " is already active");
			}
		} 
	}
	
	public void stopListener(String listenerId) {
        MessageListenerContainer container = registry.getListenerContainer(listenerId);
        if(container != null) {
			if(container.isRunning()) {
				container.stop();
				System.out.println(listenerId + " stopped...");
			} else  {
				System.out.println(listenerId + " is alreday inactive");
			}
		}
    }
}
