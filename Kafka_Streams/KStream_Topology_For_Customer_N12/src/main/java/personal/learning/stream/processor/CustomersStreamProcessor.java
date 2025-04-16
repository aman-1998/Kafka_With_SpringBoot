package personal.learning.stream.processor;

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
		
		KStream<String, Customer> purchasedByMobileStream = builder.stream(purchasedByMobileTopic, 
				Consumed.with(Serdes.String(), customerSerde));
		
		KStream<String, Customer> purchasedByWebStream = builder.stream(purchasedByWebTopic, 
				Consumed.with(Serdes.String(), customerSerde));
		
		purchasedByMobileStream.merge(purchasedByWebStream).to(purchasedByCustomer, 
															 Produced.with(Serdes.String(), customerSerde));
	}
}
