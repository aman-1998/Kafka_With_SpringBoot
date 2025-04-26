package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import personal.learning.dto.Customer;

@Configuration
public class CustomerUppercaseStreamProcessor {
	
	@Value("${test.topic.customer.name}")
	public String customerTopic;
	
	@Value("${test.topic.customer.uppercase.name}")
	public String customerUppercaseTopic;
	
	@Bean
	public KStream<String, Customer> kStreamCustomerUpperCase(StreamsBuilder builder) {
		KStream<String, Customer> sourceStream = builder.stream(customerTopic, 
				Consumed.with(Serdes.String(), new JsonSerde<>(Customer.class)));
		
		KStream<String, Customer> upperCaseStream = sourceStream.mapValues(customer -> {
			
			customer.setName(customer.getName().toUpperCase());
			return customer;
		});
		
		// Send modified Customer object to another topic
        upperCaseStream.to(customerUppercaseTopic, Produced.with(Serdes.String(), new JsonSerde<>(Customer.class)));
		
		//return sourceStream;
		return upperCaseStream;
		/*
		 * If the method is just configuring the topology and doesn't need the KStream elsewhere, 
		 * returning sourceStream is fine.
		 * 
		 * If you want clarity, it's better to return upperCaseStream since that represents the 
		 * actual transformed data.
		 */
	}
}
