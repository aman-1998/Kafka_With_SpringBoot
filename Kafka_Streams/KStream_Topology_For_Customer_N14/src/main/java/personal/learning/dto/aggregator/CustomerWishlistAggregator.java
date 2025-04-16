package personal.learning.dto.aggregator;

import org.apache.kafka.streams.kstream.Aggregator;

import personal.learning.dto.CustomerAggregate;
import personal.learning.dto.CustomerWishlist;

public class CustomerWishlistAggregator implements Aggregator<String, CustomerWishlist, CustomerAggregate> {

	@Override
	public CustomerAggregate apply(String key, CustomerWishlist value, CustomerAggregate aggregate) {
		
		// Write any logic in aggregator
		aggregate.putWishItem(value.getItemName(), value.getTimeStamp());
		return aggregate;
	}

}
