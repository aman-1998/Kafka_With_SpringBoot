package personal.learning.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import personal.learning.listener.service.KafkaListenerService;

@Component
public class FoodOrderListenerStartStopScheduler {
	
	@Autowired
	private KafkaListenerService kafkaListenerService;
	
	@Scheduled(cron = "0 30 13 * * *")
	public void startListener() {
		kafkaListenerService.startListener("myListener2");
	}
	
	@Scheduled(cron = "0 25 13 * * *")
	public void stopListener() {
		kafkaListenerService.stopListener("myListener2");
	}
}
