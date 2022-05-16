package com.jet.restaurant.frontend.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantStateMessage {

    private Long restaurantId;

    private String state;
}
