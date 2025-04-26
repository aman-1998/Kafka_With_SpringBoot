package personal.learning.dto.aggregator;

import org.apache.kafka.streams.kstream.Aggregator;

import personal.learning.dto.CustomerAggregate;
import personal.learning.dto.CustomerShopingCart;

public class CustomerShoppingCartAggregator implements Aggregator<String, CustomerShopingCart, CustomerAggregate> {

    @Override
    public CustomerAggregate apply(String key, CustomerShopingCart value, CustomerAggregate aggregate) {
        // Your logic here
        aggregate.putShoppingCartItem(value.getItemName(), value.getTimeStamp());
        return aggregate;
    }
}
