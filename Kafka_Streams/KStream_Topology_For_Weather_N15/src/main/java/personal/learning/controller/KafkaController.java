package personal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import personal.learning.dto.Humidity;
import personal.learning.dto.Precipitation;
import personal.learning.publisher.MessagePublisher;

@RestController
public class KafkaController {
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@PostMapping("/send/humidity")
	public ResponseEntity<?> send(@RequestBody Humidity message) {
		try {
			System.out.println("Humidity -> " + message);
			messagePublisher.sendHumidityMsg(message);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			System.out.println("Exception occurred due to " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
	@PostMapping("/send/precipitation")
	public ResponseEntity<?> send(@RequestBody Precipitation message) {
		try {
			System.out.println("Precipitation -> " + message);
			messagePublisher.sendPrecipitationMsg(message);
			return ResponseEntity.ok("Message sent successfully...");
		} catch(Exception ex) {
			System.out.println("Exception occurred: " + ex.getMessage());
			System.out.println("Exception occurred due to " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .build();
		}
	}
	
}
