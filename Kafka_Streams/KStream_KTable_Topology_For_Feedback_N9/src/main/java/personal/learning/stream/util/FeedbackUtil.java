package personal.learning.stream.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;

import personal.learning.dto.Feedback;

public class FeedbackUtil {
	
	public static Set<String> POSITIVE_WORDS = Set.of("good", "positive", "helpful", 
											          "amazing", "satisfied", "satisfy", 
											          "happy", "great", "love");
	
	public static Set<String> BAD_WORDS = Set.of("bad", "negative", "worst", 
									             "pathetic", "unsatisfied", "fraud", 
									             "unhappy", "hate");
	
	public static KeyValueMapper<String, Feedback, Iterable<KeyValue<String, String>>> convertToWords() {
		
		return (key, feedback) -> Arrays.asList(feedback.getComment().toLowerCase()
				 						.replace("[^a-zA-Z]", " ").split("\\s+"))
				 						.stream().distinct()
				 						.map(goodWord -> KeyValue.pair(feedback.getLocation(), goodWord))
				 						.collect(Collectors.toList());
	}
	
	public static Predicate<String, String> isGood() {
		return (key, word) -> POSITIVE_WORDS.contains(word);
	}
	
	public static Predicate<String, String> isBad() {
		return (key, word) -> BAD_WORDS.contains(word);
	}

	public static Predicate<String, Feedback> isGoodFeedback() {
		
		return (key, feedback) -> {
			
			long noOfPositiveWords = Arrays.asList(feedback.getComment().toLowerCase().split("\\s+"))
										   .stream().distinct()
										   .filter(word -> FeedbackUtil.POSITIVE_WORDS.contains(word))
										   .count();
			
			return noOfPositiveWords > 0 ? true : false;
			
		};
	}

	public static Predicate<String, Feedback> isBadFeedback() {
		
		return (key, feedback) -> {
			
			long noOfPositiveWords = Arrays.asList(feedback.getComment().toLowerCase().split("\\s+"))
										   .stream().distinct()
										   .filter(word -> FeedbackUtil.BAD_WORDS.contains(word))
										   .count();
			
			return noOfPositiveWords > 0 ? true : false;
			
		};
	}
}
