package com.jet.restaurant.state_management.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface StateSource {

    String STATE_CHANGE_OUTPUT = "state-change-output";

    @Output(STATE_CHANGE_OUTPUT)
    MessageChannel stateChangeOutput();
}
