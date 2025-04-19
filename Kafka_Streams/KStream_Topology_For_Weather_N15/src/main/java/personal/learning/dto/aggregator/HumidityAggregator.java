package personal.learning.dto.aggregator;

import org.apache.kafka.streams.kstream.Aggregator;

import personal.learning.dto.Humidity;
import personal.learning.dto.Weather;

public class HumidityAggregator implements Aggregator<String, Humidity, Weather> {

	@Override
	public Weather apply(String key, Humidity humidity, Weather weather) {
		
		weather.setHumidityPercentage(humidity.getHumidityPercentage());
		return weather;
	}
	
}
