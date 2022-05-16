package com.jet.restaurant.state_management.service.impl;

import java.util.Optional;
import com.jet.restaurant.state_management.constant.State;
import com.jet.restaurant.state_management.entity.RestaurantState;
import com.jet.restaurant.state_management.repository.RestaurantStateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantStateServiceImplTest {

    @Mock
    private RestaurantStateRepository restaurantStateRepository;

    @InjectMocks
    private RestaurantStateServiceImpl restaurantStateService;

    private RestaurantState restaurantState;

    @BeforeEach
    void setUp() {

        restaurantState = RestaurantState.builder()
                .restaurantId(1636504647789318145L)
                .restaurantName("The Gourmet Kitchen")
                .openState(State.OPEN)
                .build();
    }

    @Test
    void updateRestaurantState() {

        // given
        given(restaurantStateRepository.save(any(RestaurantState.class))).willReturn(restaurantState);

        // when
        restaurantState.setOpenState(State.CLOSED);
        RestaurantState updatedRestaurantState = restaurantStateService.updateRestaurantState(restaurantState);

        // then
        assertThat(updatedRestaurantState.getOpenState()).isEqualTo(State.CLOSED);
    }

    @Test
    void getExistedRestaurantState() {

        // given
        given(restaurantStateRepository.getRestaurantStateByRestaurantId(anyLong())).willReturn(Optional.of(restaurantState));

        // when
        Optional<RestaurantState> existedRestaurantState = restaurantStateService.getRestaurantState(this.restaurantState.getRestaurantId());

        // then
        assertThat(existedRestaurantState.get()).isNotNull();
        assertThat(existedRestaurantState.get().getOpenState()).isEqualTo(restaurantState.getOpenState());
    }

    @Test
    void getNonExistedRestaurantState() {

        // given
        given(restaurantStateRepository.getRestaurantStateByRestaurantId(anyLong())).willReturn(Optional.empty());

        // when
        Optional<RestaurantState> nullRestaurantState = restaurantStateService.getRestaurantState(this.restaurantState.getRestaurantId());

        // then
        assertThat(nullRestaurantState.isEmpty()).isTrue();
    }
}