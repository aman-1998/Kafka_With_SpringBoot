package personal.learning.stream.processor;

import java.util.List;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.Customer;

@Component
public class CustomersStreamProcessor {
	
	@Value("${test.topic.purchase.mobile}")
	public String purchasedByMobileTopic;
	
	@Value("${test.topic.purchase.web}")
	public String purchasedByWebTopic;
	
	@Value("${test.topic.purchase}")
	public String purchasedByCustomer;
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		JsonSerde<Customer> customerSerde = new JsonSerde<>(Customer.class);
		
		List<String> listOfSourceTopics = List.of(purchasedByMobileTopic, purchasedByWebTopic);
		
		KStream<String, Customer> purchasedByCustomerStream = builder.stream(listOfSourceTopics, 
				Consumed.with(Serdes.String(), customerSerde));
		
		purchasedByCustomerStream.to(purchasedByCustomer, Produced.with(Serdes.String(), customerSerde));
	}
}
