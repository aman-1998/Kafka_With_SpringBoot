package personal.learning.dto.aggregator;

import org.apache.kafka.streams.kstream.Aggregator;

import personal.learning.dto.Precipitation;
import personal.learning.dto.Weather;

public class PrecipitationAggregator implements Aggregator<String, Precipitation, Weather>{

	@Override
	public Weather apply(String key, Precipitation precipitation, Weather weather) {
		
		weather.setPrecipitationPercentage(precipitation.getPrecipitationPercentage());
		return weather;
	}

}
