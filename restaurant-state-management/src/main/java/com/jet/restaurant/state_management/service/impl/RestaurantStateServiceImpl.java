package com.jet.restaurant.state_management.service.impl;

import com.jet.restaurant.state_management.constant.State;
import com.jet.restaurant.state_management.entity.RestaurantState;
import com.jet.restaurant.state_management.message.RestaurantStateMessage;
import com.jet.restaurant.state_management.repository.RestaurantStateRepository;
import com.jet.restaurant.state_management.service.RestaurantStateService;
import com.jet.restaurant.state_management.source.StateSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantStateServiceImpl implements RestaurantStateService {

    private RestaurantStateRepository restaurantStateRepository;
    private StateSource stateSource;

    public RestaurantStateServiceImpl(RestaurantStateRepository restaurantStateRepository, StateSource stateSource) {
        this.restaurantStateRepository = restaurantStateRepository;
        this.stateSource = stateSource;
    }

    @Override
    public RestaurantState updateRestaurantState(RestaurantState restaurantState) {

        return restaurantStateRepository.save(restaurantState);
    }

    @Override
    public Optional<RestaurantState> getRestaurantState(Long restaurantId) {

        return restaurantStateRepository.getRestaurantStateByRestaurantId(restaurantId);
    }

    @Override
    public boolean sendRestaurantState(RestaurantState restaurantState) {

        RestaurantStateMessage message = RestaurantStateMessage.builder()
                                            .restaurantId(restaurantState.getRestaurantId())
                                            .state(restaurantState.getOpenState())
                                            .build();

        Message<RestaurantStateMessage> springMessage = MessageBuilder.withPayload(message).build();
        return stateSource.stateChangeOutput().send(springMessage);
    }
}
