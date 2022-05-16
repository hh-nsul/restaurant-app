package com.jet.restaurant.state_management.entity;

import com.jet.restaurant.state_management.constant.State;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("restaurant_state")
public class RestaurantState {

    @Id
    private Long restaurantId;

    @Indexed
    private String restaurantName;

    private String openState;

}
