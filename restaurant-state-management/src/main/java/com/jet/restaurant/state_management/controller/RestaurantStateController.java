package com.jet.restaurant.state_management.controller;

import com.jet.restaurant.state_management.entity.RestaurantState;
import com.jet.restaurant.state_management.service.RestaurantStateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantStateController {

    private RestaurantStateService restaurantStateService;

    public RestaurantStateController(RestaurantStateService restaurantStateService) {
        this.restaurantStateService = restaurantStateService;
    }

    @PutMapping("/{id}/{state}")
    public ResponseEntity<RestaurantState> updateRestaurantState(@PathVariable("id") Long restaurantId,
                                                                 @PathVariable("state") String openState) {

        return restaurantStateService.getRestaurantState(restaurantId)
                .map(savedRestaurantState -> {

                    // Update the state when it is changed
                    if (!openState.equals(savedRestaurantState.getOpenState())) {
                        savedRestaurantState.setOpenState(openState);
                        RestaurantState updatedRestaurantState = restaurantStateService.updateRestaurantState(savedRestaurantState);

                        restaurantStateService.sendRestaurantState(updatedRestaurantState);

                        return new ResponseEntity<>(updatedRestaurantState, HttpStatus.ACCEPTED);
                    } else {
                        return new ResponseEntity<>(savedRestaurantState, HttpStatus.OK);
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantState> getRestaurantState(@PathVariable("id") Long restaurantId) {

        return restaurantStateService.getRestaurantState(restaurantId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}