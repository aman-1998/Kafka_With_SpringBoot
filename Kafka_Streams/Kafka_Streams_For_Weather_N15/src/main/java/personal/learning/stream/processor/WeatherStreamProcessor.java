package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.Humidity;
import personal.learning.dto.Precipitation;
import personal.learning.dto.Weather;
import personal.learning.dto.aggregator.HumidityAggregator;
import personal.learning.dto.aggregator.PrecipitationAggregator;

@Component
public class WeatherStreamProcessor {
	
	@Value("${test.topic.humidity}")
	public String humidityTopic;
	
	@Value("${test.topic.precipitation}")
	public String precipitationTopic;
	
	@Value("${test.topic.weather}")
	public String weatherTopic;
	
	private static final HumidityAggregator HUMIDITY_AGGREGATOR = new HumidityAggregator();
	private static final PrecipitationAggregator PRECIPITATION_AGGREGATOR = new PrecipitationAggregator();
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		
		JsonSerde<Humidity> humiditySerde = new JsonSerde<>(Humidity.class);
		JsonSerde<Precipitation> precipitationSerde = new JsonSerde<>(Precipitation.class);
		JsonSerde<Weather> weatherSerde = new JsonSerde<>(Weather.class);
		
		KGroupedStream<String, Humidity> groupedHumidityStream = builder.stream(humidityTopic, 
				Consumed.with(Serdes.String(), humiditySerde)).groupByKey();
		
		KGroupedStream<String, Precipitation> groupedPrecipitationStream = builder.stream(precipitationTopic, 
				Consumed.with(Serdes.String(), precipitationSerde)).groupByKey();
		
		KStream<String, Weather> weatherStream = groupedHumidityStream.cogroup(HUMIDITY_AGGREGATOR)
								 .cogroup(groupedPrecipitationStream, PRECIPITATION_AGGREGATOR)
								 .aggregate(() -> new Weather(), Materialized.with(Serdes.String(), weatherSerde))
								 .toStream()
								 .map((key, value) -> {
									 value.setLocation(key);
									 if((Integer.valueOf(value.getHumidityPercentage().substring(0, value.getHumidityPercentage().indexOf("%")))  >= 80
											 && Integer.valueOf(value.getHumidityPercentage().substring(0, value.getHumidityPercentage().indexOf("%")))  <= 95)
										&& (Double.valueOf(value.getPrecipitationPercentage().substring(0, value.getPrecipitationPercentage().indexOf("mm")))  >= 6.5
												 && Double.valueOf(value.getPrecipitationPercentage().substring(0, value.getPrecipitationPercentage().indexOf("mm")))  <= 9.0)) {
										 value.setRainPossibility(true);
										 value.setComment("Rainy day in " + value.getLocation());
									 } else {
										 value.setRainPossibility(false);
										 value.setComment("Sunny day in " + value.getLocation());
									 }
						
									 return KeyValue.pair(key, value);
								 });
		
		weatherStream.to(weatherTopic, Produced.with(Serdes.String(), weatherSerde));
	}
}
