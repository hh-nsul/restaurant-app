package com.jet.restaurant.state_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jet.restaurant.state_management.constant.State;
import com.jet.restaurant.state_management.entity.RestaurantState;
import com.jet.restaurant.state_management.service.RestaurantStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest
class RestaurantStateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantStateService restaurantStateService;

    @Autowired
    private ObjectMapper objectMapper;

    private RestaurantState restaurantState;

    @BeforeEach
    void setUp() {
        restaurantState = RestaurantState.builder()
                .restaurantId(1636504647789318145L)
                .restaurantName("The Gourmet Kitchen")
                .openState(State.UNKNOWN)
                .build();
    }

    @Test
    void updateRestaurantState() throws Exception {

        // given
        given(restaurantStateService.getRestaurantState(anyLong())).willReturn(Optional.of(restaurantState));

        RestaurantState updatedRestaurantState = RestaurantState.builder()
                                                    .restaurantId(restaurantState.getRestaurantId())
                                                    .restaurantName(restaurantState.getRestaurantName())
                                                    .openState(State.OPEN)
                                                    .build();

        given(restaurantStateService.updateRestaurantState(any(RestaurantState.class))).willReturn(updatedRestaurantState);
        given(restaurantStateService.sendRestaurantState(any(RestaurantState.class))).willReturn(true);

        // when
        ResultActions response = mockMvc.perform(put("/restaurant/{id}/{state}",
                                                        restaurantState.getRestaurantId(),
                                                        State.OPEN)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(updatedRestaurantState)));

        // then
        response.andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.openState", is(State.OPEN)));

    }

    @Test
    void updateRestaurantStateUnchanged() throws Exception {

        // when
        given(restaurantStateService.getRestaurantState(anyLong())).willReturn(Optional.of(restaurantState));

        // given
        ResultActions response = mockMvc.perform(put("/restaurant/{id}/{state}",
                                            restaurantState.getRestaurantId(),
                                            restaurantState.getOpenState())
                                            .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateNonExistedRestaurantState() throws Exception {

        // when
        RestaurantState nonExistedRestaurantState = RestaurantState.builder()
                                                        .restaurantId(404L)
                                                        .restaurantName("Non-Existed Restaurant")
                                                        .openState(State.UNKNOWN)
                                                        .build();

        given(restaurantStateService.getRestaurantState(anyLong())).willReturn(Optional.empty());

        // given
        ResultActions response = mockMvc.perform(put("/restaurant/{id}/{state}",
                                                    nonExistedRestaurantState.getRestaurantId(),
                                                    nonExistedRestaurantState.getOpenState())
                                                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void getRestaurantState() throws Exception {

        // when
        given(restaurantStateService.getRestaurantState(anyLong())).willReturn(Optional.of(restaurantState));

        // given
        ResultActions response = mockMvc.perform(get("/restaurant/{id}",
                                                    restaurantState.getRestaurantId())
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(objectMapper.writeValueAsString(restaurantState)));

        // then
        response.andDo(print()).andExpect(status().isOk());
    }
}