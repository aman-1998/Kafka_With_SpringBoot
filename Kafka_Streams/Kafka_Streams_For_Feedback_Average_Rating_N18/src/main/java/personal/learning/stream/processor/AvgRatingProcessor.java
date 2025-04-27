package personal.learning.stream.processor;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.processor.api.FixedKeyProcessor;
import org.apache.kafka.streams.processor.api.FixedKeyProcessorContext;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;
import org.apache.kafka.streams.state.KeyValueStore;

import personal.learning.dto.AvgFeedbackRating;
import personal.learning.dto.Feedback;
import personal.learning.dto.TotalRatingCount;

public class AvgRatingProcessor implements FixedKeyProcessor<String, Feedback, AvgFeedbackRating> {

	private FixedKeyProcessorContext<String, AvgFeedbackRating> processorContext;
	
	private final String stateStoreName;
	
	private KeyValueStore<String, TotalRatingCount> ratingStateStore;
	
	public AvgRatingProcessor(String stateStoreName) {
		if(StringUtils.isBlank(stateStoreName)) {
			throw new IllegalArgumentException("State store name can't be empty");
		}
		this.stateStoreName = stateStoreName;
	}
	
	@Override
	public void init(FixedKeyProcessorContext<String, AvgFeedbackRating> context) {
		this.processorContext = context;
		this.ratingStateStore = this.processorContext.getStateStore(stateStoreName);
	}

	@Override
	public void process(FixedKeyRecord<String, Feedback> record) {
		Feedback orginalFeedback = record.value();
		TotalRatingCount totalRatingCount = Optional.ofNullable(ratingStateStore.get(orginalFeedback.getLocation()))
												    .orElse(new TotalRatingCount());
		int newSumOfRating = totalRatingCount.getSumOfRating() + orginalFeedback.getRating();
		int newCountOfRating = totalRatingCount.getCountOfRating() + 1;
		totalRatingCount.setSumOfRating(newSumOfRating);
		totalRatingCount.setCountOfRating(newCountOfRating);
		
		ratingStateStore.put(orginalFeedback.getLocation(), totalRatingCount);
		
		AvgFeedbackRating avgFeedbackRating = new AvgFeedbackRating();
		avgFeedbackRating.setLocation(orginalFeedback.getLocation());
		avgFeedbackRating.setAvgRating(Math.round((double)newSumOfRating/newCountOfRating * 10.0) / 10.0);
		
		processorContext.forward(record.withValue(avgFeedbackRating));
	}

}
