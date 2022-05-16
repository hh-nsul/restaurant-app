package com.jet.restaurant.state_management.repository;

import com.jet.restaurant.state_management.config.SnowflakeProperties;
import com.jet.restaurant.state_management.constant.State;
import com.jet.restaurant.state_management.entity.RestaurantState;
import com.jet.restaurant.state_management.util.SnowflakeManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestaurantStateRepositoryTest {

    @Autowired
    private RestaurantStateRepository restaurantStateRepository;

    private RestaurantState restaurantState;

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    @BeforeAll
    void setUp() throws Exception {

        SnowflakeManager snowflakeManager = new SnowflakeManager(snowflakeProperties.getMachineId(),
                snowflakeProperties.getDataCenterId());

        restaurantState = RestaurantState.builder()
                .restaurantId(snowflakeManager.nextValue())
                .restaurantName("The Gourmet Kitchen")
                .openState(State.OPEN)
                .build();
    }

    @AfterAll
    void tearDown() {
        restaurantStateRepository.deleteById(restaurantState.getRestaurantId());
    }

    @Test
    @Order(1)
    void saveRestaurantStateTest() {
        RestaurantState savedRestaurantState = restaurantStateRepository.save(restaurantState);
        assertThat(savedRestaurantState.getRestaurantName()).isEqualTo(restaurantState.getRestaurantName());
    }

    @Test
    @Order(2)
    void getRestaurantStateTest() {
        Optional<RestaurantState> optionalRestaurantState =
                restaurantStateRepository.getRestaurantStateByRestaurantId(restaurantState.getRestaurantId());

        if (optionalRestaurantState.isPresent()) {
            assertThat(optionalRestaurantState.get()).isNotNull();
        }
    }

}