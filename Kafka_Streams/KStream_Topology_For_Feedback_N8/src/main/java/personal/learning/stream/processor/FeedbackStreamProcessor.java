package personal.learning.stream.processor;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.Feedback;
import personal.learning.stream.util.FeedbackUtil;

@Component
public class FeedbackStreamProcessor {
	
	@Value("${test.topic.feedback}")
	public String feedbackTopic;
	
	@Value("${test.topic.positive.word}")
	public String positiveWordTopic;
	
	@Value("${test.topic.negative.word}")
	public String negativeWordTopic;
	
	@Value("${test.topic.good.feedback}")
	public String goodFeedbackTopic;
	
	@Value("${test.topic.bad.feedback}")
	public String badFeedbackTopic;
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		JsonSerde<Feedback> feedbackSerde = new JsonSerde<>(Feedback.class);
		
		KStream<String, Feedback> sourceStream = builder.stream(feedbackTopic, 
				Consumed.with(Serdes.String(), feedbackSerde));
		
		
		sourceStream.flatMap(FeedbackUtil.convertToWords())
					.split()
					.branch(FeedbackUtil.isGood(), Branched.<String, String>withConsumer(ks -> ks.to(positiveWordTopic)))
					.branch(FeedbackUtil.isBad(), Branched.<String, String>withConsumer(ks -> ks.to(negativeWordTopic)));
		
		
		sourceStream.split().branch(FeedbackUtil.isGoodFeedback(), Branched.<String, Feedback>withConsumer(ks -> ks.to(goodFeedbackTopic)))
						    .branch(FeedbackUtil.isBadFeedback(), Branched.<String, Feedback>withConsumer(ks -> ks.to(badFeedbackTopic)));
							
		
		KStream<String, Feedback> goodFeedbackStream = sourceStream.filter((key, feedback) -> {
			
			long noOfPositiveWords = Arrays.asList(feedback.getComment().toLowerCase().split("\\s+"))
										   .stream().distinct()
										   .filter(word -> FeedbackUtil.POSITIVE_WORDS.contains(word))
										   .count();
			
			return noOfPositiveWords > 0 ? true : false;
			
		});
		
	}
}
