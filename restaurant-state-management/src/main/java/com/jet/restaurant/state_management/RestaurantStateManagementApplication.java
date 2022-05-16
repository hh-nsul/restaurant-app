package com.jet.restaurant.state_management;

import com.jet.restaurant.state_management.source.StateSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;


@SpringBootApplication
@EnableBinding(StateSource.class)
public class RestaurantStateManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantStateManagementApplication.class, args);
    }

}
