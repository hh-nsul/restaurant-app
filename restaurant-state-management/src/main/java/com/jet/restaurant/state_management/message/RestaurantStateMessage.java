package com.jet.restaurant.state_management.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantStateMessage {

    private Long restaurantId;

    private String state;
}
