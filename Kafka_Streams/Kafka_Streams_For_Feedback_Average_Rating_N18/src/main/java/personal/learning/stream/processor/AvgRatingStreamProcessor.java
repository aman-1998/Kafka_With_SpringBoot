package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.AvgFeedbackRating;
import personal.learning.dto.Feedback;
import personal.learning.dto.TotalRatingCount;

@Component
public class AvgRatingStreamProcessor {
	
	@Value("${test.topic.feedback}")
	public String feedbackTopic;
	
	@Value("${test.topic.average.rating}")
	public String averageRatingTopic;
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		
		Serde<String> stringSerde = Serdes.String();
		JsonSerde<Feedback> feedbackSerde = new JsonSerde<>(Feedback.class);
		JsonSerde<AvgFeedbackRating> avgFeedbackRatingSerde = new JsonSerde<>(AvgFeedbackRating.class);
		
		JsonSerde<TotalRatingCount> totalRatingCountSerde = new JsonSerde<>(TotalRatingCount.class);
		String totalRatingCountStateStoreName = "totalRatingCountStateStore";
		//KeyValueBytesStoreSupplier kayValueStoreSupplier = Stores.inMemoryKeyValueStore(totalRatingCountStateStoreName);
		KeyValueBytesStoreSupplier persistentKeyValueStore = Stores.persistentKeyValueStore(totalRatingCountStateStoreName);
		StoreBuilder<KeyValueStore<String, TotalRatingCount>> storeBuilder = Stores.keyValueStoreBuilder(
													persistentKeyValueStore,
												    stringSerde,
												    totalRatingCountSerde);
		
		builder.addStateStore(storeBuilder);
		
		KStream<String, AvgFeedbackRating> avgFeedbackRatingStream = builder.stream(feedbackTopic, Consumed.with(stringSerde, feedbackSerde))
																	   .processValues(() -> new AvgRatingProcessor(totalRatingCountStateStoreName), totalRatingCountStateStoreName);
		
		avgFeedbackRatingStream.to(averageRatingTopic, Produced.with(Serdes.String(), avgFeedbackRatingSerde));
		
	}
}
