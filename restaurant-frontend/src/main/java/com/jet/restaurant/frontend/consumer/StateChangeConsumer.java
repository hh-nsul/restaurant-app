package com.jet.restaurant.frontend.consumer;

import com.jet.restaurant.frontend.message.RestaurantStateMessage;
import com.jet.restaurant.frontend.sink.StateSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StateChangeConsumer {

    @StreamListener(StateSink.STATE_CHANGE_INPUT)
    public void onMessage(@Payload RestaurantStateMessage message) {
        log.info("[onMessage] [Thread: {}, Message Content: {}]", Thread.currentThread().getId(), message);
    }
}
