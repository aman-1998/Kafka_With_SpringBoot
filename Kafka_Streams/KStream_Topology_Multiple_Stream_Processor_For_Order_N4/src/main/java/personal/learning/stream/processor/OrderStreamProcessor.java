package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.Order;
import personal.learning.dto.OrderReward;
import personal.learning.dto.OrderSummary;
import personal.learning.stream.util.OrderUtil;

@Component
public class OrderStreamProcessor {
	
	@Value("${test.topic.order}")
	public String orderTopic;
	
	@Value("${test.topic.order.masked.creditcard}")
	public String orderMaskedCreditCardTopic;
	
	@Value("${test.topic.order.summary}")
	public String orderSummaryTopic;
	
	@Value("${test.topic.order.reward}")
	public String orderRewardTopic;
	
	@Value("${test.topic.order.storage}")
	public String orderStorageTopic;
	
	@Autowired
	public void processOrders(StreamsBuilder builder) {
		JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);
		JsonSerde<OrderSummary> orderSummarySerde = new JsonSerde<>(OrderSummary.class);
		JsonSerde<OrderReward> orderRewardSerde = new JsonSerde<>(OrderReward.class);
		
		KStream<String, Order> sourceStream = builder.stream(orderTopic, 
				Consumed.with(Serdes.String(), orderSerde));
		
		KStream<String, Order> maskedCreditCardStream = sourceStream.mapValues(order -> 
												    OrderUtil.maskCreditCardNumber(order));
		
		//maskedCreditCardStream.print(Printed.<String, Order>toSysOut().withLabel("Masked credit card stream:::"));
		
		maskedCreditCardStream.mapValues(order -> OrderUtil.convertToOrderSummary(order))
						      .to(orderSummaryTopic, Produced.with(Serdes.String(), orderSummarySerde));
		
		maskedCreditCardStream.filter((key, order) -> order.getQuantity() > 200)
							  .mapValues(order -> OrderUtil.convertToOrderReward(order))
							  .to(orderRewardTopic, Produced.with(Serdes.String(), orderRewardSerde));
		
		maskedCreditCardStream.to(orderStorageTopic, Produced.with(Serdes.String(), orderSerde));
		
	}
}
