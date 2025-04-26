package personal.learning.stream.processor;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import personal.learning.dto.Customer;

@Configuration
public class CustomerUppercaseStreamProcessor {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${test.topic.customer.name}")
	public String customerTopic;
	
	@Value("${test.topic.customer.uppercase.name}")
	public String customerUppercaseTopic;
	
	@Bean
	public KStream<String, String> kStreamCustomerUpperCase(StreamsBuilder builder) {
		
		KStream<String, String> sourceStream = builder.stream(customerTopic, 
				Consumed.with(Serdes.String(), Serdes.String()));
		
		KStream<String, String> upperCaseStream = sourceStream.mapValues(customerMsg -> {
			
			String modifiedCustomerMsg = StringUtils.EMPTY;
			try {
				Customer customer = objectMapper.readValue(customerMsg, Customer.class);
				customer.setName(customer.getName().toUpperCase());
				
				modifiedCustomerMsg = objectMapper.writeValueAsString(customer);
				return modifiedCustomerMsg;
			} catch(Exception ex) {
				
			}
			return modifiedCustomerMsg;
			
		});
		
		// Send modified Customer object to another topic
        upperCaseStream.to(customerUppercaseTopic);
		
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
