package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestUppercaseStreamProcessor {
	
	@Value("${test.topic.name}")
	public String testTopic;
	
	@Value("${test.topic.uppercase.name}")
	public String testUppercaseTopic;
	
	@Bean
	public KStream<String, String> kStreamUpperCase(StreamsBuilder builder) {
		KStream<String, String> sourceStream = builder.stream(testTopic, 
				Consumed.with(Serdes.String(), Serdes.String()));
		
		KStream<String, String> upperCaseStream = sourceStream.mapValues(sentence -> sentence.toUpperCase());
		
		upperCaseStream.to(testUppercaseTopic);
		
		return sourceStream;
	}
}
