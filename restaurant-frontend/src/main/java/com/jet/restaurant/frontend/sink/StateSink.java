package com.jet.restaurant.frontend.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface StateSink {

    String STATE_CHANGE_INPUT = "state-change-input";

    @Input(STATE_CHANGE_INPUT)
    SubscribableChannel stateChangeInput();
}
