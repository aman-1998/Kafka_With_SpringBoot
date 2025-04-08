package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
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
	
	@Value("${test.topic.negative.word}")
	public String negativeWordTopic;
	
	@Value("${test.topic.good.feedback}")
	public String goodFeedbackTopic;
	
	@Value("${test.topic.bad.feedback}")
	public String badFeedbackTopic;
	
	@Value("${test.topic.positive.word.count}")
	public String positiveWordCountTopic;
	
	@Value("${test.topic.negative.word.count}")
	public String negativeWordCountTopic;
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		JsonSerde<Feedback> feedbackSerde = new JsonSerde<>(Feedback.class);
		
		KStream<String, Feedback> sourceStream = builder.stream(feedbackTopic, 
				Consumed.with(Serdes.String(), feedbackSerde));
		
		sourceStream.flatMap(FeedbackUtil.convertToWords()).split().branch(FeedbackUtil.isGood(), 
			Branched.<String, String>withConsumer(ks -> {
				ks.to(positiveWordTopic);
				ks.groupByKey().count().toStream().to(positiveWordCountTopic, 
													  Produced.with(Serdes.String(), Serdes.Long()));
		})).branch(FeedbackUtil.isBad(), 
			Branched.<String, String>withConsumer(ks -> {
				ks.to(negativeWordTopic);
				ks.groupByKey().count().toStream().to(negativeWordCountTopic, 
													  Produced.with(Serdes.String(), Serdes.Long()));	
		}));
		
	}
}
