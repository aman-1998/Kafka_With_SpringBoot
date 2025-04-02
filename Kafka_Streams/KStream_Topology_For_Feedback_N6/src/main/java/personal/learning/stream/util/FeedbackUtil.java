package personal.learning.stream.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.kafka.streams.kstream.ValueMapper;

import personal.learning.dto.Feedback;

public class FeedbackUtil {
	
	public static Set<String> POSITIVE_WORDS = Set.of("good", "positive", "amazing", "satisfied", "satisfy", "happy", "great");
	
	public static ValueMapper<Feedback, Iterable<String>> mapperPositiveWords() {
		
		return feedback -> Arrays.asList(feedback.getComment().toLowerCase().split("\\s+"))
								 .stream().filter(word -> POSITIVE_WORDS.contains(word))
								 .collect(Collectors.toList());
	}

}
