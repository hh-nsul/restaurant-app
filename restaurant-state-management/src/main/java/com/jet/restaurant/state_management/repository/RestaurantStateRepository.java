package com.jet.restaurant.state_management.repository;

import com.jet.restaurant.state_management.entity.RestaurantState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantStateRepository extends MongoRepository<RestaurantState, Long> {

    Optional<RestaurantState> getRestaurantStateByRestaurantId(Long restaurantId);

}
