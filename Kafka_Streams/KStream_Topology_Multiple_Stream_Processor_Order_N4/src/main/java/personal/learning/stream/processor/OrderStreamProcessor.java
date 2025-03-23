package personal.learning.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import personal.learning.dto.Order;
import personal.learning.stream.util.CommodityUtil;

@Component
public class OrderStreamProcessor {
	
	@Value("${test.topic.order}")
	public String orderTopic;
	
	@Value("${test.topic.order.masked.creditcard}")
	public String orderMaskedCreditCardTopic;
	
	@Autowired
	public void processOrders(StreamsBuilder builder) {
		JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);
		
		KStream<String, Order> sourceStream = builder.stream(orderTopic, 
				Consumed.with(Serdes.String(), orderSerde));
		
		KStream<String, Order> maskedCreditCardStream = sourceStream.mapValues(order -> 
												    CommodityUtil.maskCreditCardNumber(order));
		
		maskedCreditCardStream.print(Printed.<String, Order>toSysOut().withLabel("Masked credit card stream:::"));
		
		maskedCreditCardStream.to(orderMaskedCreditCardTopic, Produced.with(Serdes.String(), orderSerde));
	}
}
