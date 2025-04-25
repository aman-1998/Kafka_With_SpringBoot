package personal.learning.dto;

import java.time.Instant;

import org.apache.kafka.streams.processor.api.FixedKeyProcessor;
import org.apache.kafka.streams.processor.api.FixedKeyProcessorContext;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;

public class TimeBoundVoting implements FixedKeyProcessor<String, VotingMachine, VotingMachine>{

	private final long voteStartTimeMilli;
	
	private final long voteEndTimeMilli;
	
	private FixedKeyProcessorContext<String, VotingMachine> processorContext;
	
	public TimeBoundVoting(Instant voteStartTimeIns, Instant voteEndTimeIns) {
		this.voteStartTimeMilli = voteStartTimeIns.getEpochSecond()*1000;
		this.voteEndTimeMilli = voteEndTimeIns.getEpochSecond()*1000;
	}
	
	public void init(FixedKeyProcessorContext<String, VotingMachine> context) {
		this.processorContext = context;
	}
	
	@Override
	public void process(FixedKeyRecord<String, VotingMachine> record) {
		long recordTime = processorContext.currentSystemTimeMs();
		if(voteStartTimeMilli <= recordTime && recordTime <= voteEndTimeMilli) {
			processorContext.forward(record.withValue(record.value()));
		}
		
	}

}
