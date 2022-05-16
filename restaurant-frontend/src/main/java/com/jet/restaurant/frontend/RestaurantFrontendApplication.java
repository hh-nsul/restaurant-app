package com.jet.restaurant.frontend;

import com.jet.restaurant.frontend.sink.StateSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(StateSink.class)
public class RestaurantFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantFrontendApplication.class, args);
    }

}
