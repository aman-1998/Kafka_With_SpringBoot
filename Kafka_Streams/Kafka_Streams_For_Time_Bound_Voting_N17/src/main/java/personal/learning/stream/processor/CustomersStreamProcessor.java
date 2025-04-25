package personal.learning.stream.processor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.TimeBoundVoting;
import personal.learning.dto.VotingMachine;

@Component
public class CustomersStreamProcessor {
	
	@Value("${test.topic.voting}")
	public String votingTopic;
	
	@Value("${test.topic.voting.result}")
	public String votingResultTopic;
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		
		JsonSerde<VotingMachine> votingMachineSerde = new JsonSerde<>(VotingMachine.class);
		
		Instant voteStart = Instant.now();
		Instant voteEnd = voteStart.plus(5, ChronoUnit.MINUTES);
		
		KStream<String, VotingMachine> votingStream = builder.stream(votingTopic, Consumed.with(Serdes.String(), votingMachineSerde))
				.processValues(() -> new TimeBoundVoting(voteStart, voteEnd))
				.filter((key, transformedValue) -> transformedValue!=null)
				.map((key, value) -> KeyValue.pair(value.getAadharNo(), value));
		
		votingStream.toTable(Materialized.with(Serdes.String(), votingMachineSerde)).groupBy((key, value) -> KeyValue.pair(value.getParty(), value.getParty()), 
									   Grouped.with(Serdes.String(), Serdes.String())).count().toStream()
							  .to(votingResultTopic, Produced.with(Serdes.String(), Serdes.Long()));
	}
}
