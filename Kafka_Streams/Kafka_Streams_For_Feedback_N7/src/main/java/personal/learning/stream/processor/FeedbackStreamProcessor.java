package personal.learning.stream.processor;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
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
	
	@Value("${test.topic.good.feedback}")
	public String goodFeedbackTopic;
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		JsonSerde<Feedback> feedbackSerde = new JsonSerde<>(Feedback.class);
		
		KStream<String, Feedback> sourceStream = builder.stream(feedbackTopic, 
				Consumed.with(Serdes.String(), feedbackSerde));
		
		KStream<String, String> positiveWordStream = sourceStream.flatMap(
				(key, feedback) -> Arrays.asList(feedback.getComment().toLowerCase().split("\\s+"))
										 .stream().filter(word -> FeedbackUtil.POSITIVE_WORDS.contains(word))
										 .distinct()
										 .map(goodWord -> KeyValue.pair(feedback.getLocation(), goodWord))
										 .collect(Collectors.toList()));
		
		//KStream<String, String> positiveWordStream = sourceStream.flatMapValues(FeedbackUtil.mapperPositiveWords());
		
		//positiveWordStream.print(Printed.<String, String>toSysOut().withLabel("positiveWordStream:::"));
		
		positiveWordStream.to(positiveWordTopic); // Default serdes is String for both key and value
		
		KStream<String, Feedback> goodFeedbackStream = sourceStream.filter((key, feedback) -> {
			
			long noOfPositiveWords = Arrays.asList(feedback.getComment().toLowerCase().split("\\s+"))
										   .stream().distinct()
										   .filter(word -> FeedbackUtil.POSITIVE_WORDS.contains(word))
										   .count();
			
			return noOfPositiveWords > 0 ? true : false;
			
		});
		
		//goodFeedbackStream.print(Printed.<String, Feedback>toSysOut().withLabel("goodFeedbackStream:::"));
		
		goodFeedbackStream.to(goodFeedbackTopic, Produced.with(Serdes.String(), feedbackSerde));
		
	}
}
