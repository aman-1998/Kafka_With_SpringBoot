package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
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

import personal.learning.dto.CustomerAggregate;
import personal.learning.dto.CustomerShopingCart;
import personal.learning.dto.CustomerWishlist;
import personal.learning.dto.aggregator.CustomerShoppingCartAggregator;
import personal.learning.dto.aggregator.CustomerWishlistAggregator;

@Component
public class CustomersStreamProcessor {
	
	@Value("${test.topic.shopping.cart}")
	public String shoppingCartTopic;
	
	@Value("${test.topic.wishlist}")
	public String wishListTopic;
	
	@Value("${test.topic.aggregate}")
	public String aggregateTopic;
	
	private static final CustomerShoppingCartAggregator CUSTOMER_SHOPPING_CART_AGGREGATOR = new CustomerShoppingCartAggregator();
	private static final CustomerWishlistAggregator CUSTOMER_WISHLIST_AGGREGATOR = new CustomerWishlistAggregator();
	
	@Autowired
	public void processFeedback(StreamsBuilder builder) {
		
		JsonSerde<CustomerWishlist> customerWishlistSerde = new JsonSerde<>(CustomerWishlist.class);
		JsonSerde<CustomerShopingCart> customerShopingCartSerde = new JsonSerde<>(CustomerShopingCart.class);
		JsonSerde<CustomerAggregate> customerAggregateSerde = new JsonSerde<>(CustomerAggregate.class);
		
		KGroupedStream<String, CustomerShopingCart> groupedCustomerShopingCartStream = builder.stream(shoppingCartTopic, 
				Consumed.with(Serdes.String(), customerShopingCartSerde)).groupByKey();
		
		KGroupedStream<String, CustomerWishlist> groupedCustomerWishlistStream = builder.stream(wishListTopic, 
				Consumed.with(Serdes.String(), customerWishlistSerde)).groupByKey();
		
		KStream<String, CustomerAggregate> customerAggregateStream = groupedCustomerShopingCartStream.cogroup(CUSTOMER_SHOPPING_CART_AGGREGATOR)
								 .cogroup(groupedCustomerWishlistStream, CUSTOMER_WISHLIST_AGGREGATOR)
								 .aggregate(() -> new CustomerAggregate(), Materialized.with(Serdes.String(), customerAggregateSerde))
								 .toStream();
		
		customerAggregateStream.to(aggregateTopic, Produced.with(Serdes.String(), customerAggregateSerde));
	}
}
