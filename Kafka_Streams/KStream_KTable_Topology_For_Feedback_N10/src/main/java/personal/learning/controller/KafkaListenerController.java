package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.listener.service.KafkaListenerService;

@RestController
@RequestMapping("/listener")
public class KafkaListenerController {
	
	@Autowired
	private KafkaListenerService kafkaListenerService;
	
	@GetMapping("/start")
	public void startListener(@RequestParam("listenerId") String listenerId) {
		kafkaListenerService.startListener(listenerId);
	}
	
	@GetMapping("/stop")
	public void stopListener(@RequestParam("listenerId") String listenerId) {
		kafkaListenerService.stopListener(listenerId);
	}
}
