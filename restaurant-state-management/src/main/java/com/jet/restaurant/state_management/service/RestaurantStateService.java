package com.jet.restaurant.state_management.service;

import com.jet.restaurant.state_management.constant.State;
import com.jet.restaurant.state_management.entity.RestaurantState;

import java.util.Optional;

public interface RestaurantStateService {

    RestaurantState updateRestaurantState(RestaurantState restaurantState);

    Optional<RestaurantState> getRestaurantState(Long restaurantId);

    boolean sendRestaurantState(RestaurantState restaurantState);
}
