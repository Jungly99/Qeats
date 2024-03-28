/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.controller;

import static com.crio.qeats.controller.RestaurantController.CART_API;
import static com.crio.qeats.controller.RestaurantController.CART_CLEAR_API;
import static com.crio.qeats.controller.RestaurantController.CART_ITEM_API;
import static com.crio.qeats.controller.RestaurantController.GET_ORDERS_API;
import static com.crio.qeats.controller.RestaurantController.MENU_API;
import static com.crio.qeats.controller.RestaurantController.POST_ORDER_API;
import static com.crio.qeats.controller.RestaurantController.RESTAURANTS_API;
import static com.crio.qeats.controller.RestaurantController.RESTAURANT_API_ENDPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.crio.qeats.QEatsApplication;
import com.crio.qeats.services.RestaurantService;

@SpringBootTest(classes = { QEatsApplication.class })
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    private static final String RESTAURANT_API_URI = RESTAURANT_API_ENDPOINT + RESTAURANTS_API;

    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    public void invalidLatitudeResultsInBadHttpRequest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).queryParam("latitude", "91")
                .queryParam("longitude", "20").build().toUri();

        assertEquals(RESTAURANT_API_URI + "?latitude=91&longitude=20", uri.toString());

        MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

        uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).queryParam("latitude", "-91").queryParam("longitude",
                "20").build().toUri();

        assertEquals(RESTAURANT_API_URI + "?latitude=-91&longitude=20", uri.toString());

        response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8)).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void invalidLongitudeResultsInBadHttpRequest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).queryParam("latitude", "10")
                .queryParam("longitude", "181").build().toUri();

        assertEquals(RESTAURANT_API_URI + "?latitude=10&longitude=181", uri.toString());

        MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

        uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).queryParam("latitude", "10").queryParam("longitude",
                "-181").build().toUri();

        assertEquals(RESTAURANT_API_URI + "?latitude=10&longitude=-181", uri.toString());

        response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8)).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void noRequestParamResultsInBadHttpReuest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).build().toUri();

        assertEquals(RESTAURANT_API_URI, uri.toString());

        MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void missingLongitudeParamResultsInBadHttpRequest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).queryParam("latitude", "20.21").build().toUri();

        assertEquals(RESTAURANT_API_URI + "?latitude=20.21", uri.toString());

        MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void missingLatitudeParamResultsInBadHttpRequest() throws Exception {
        URI uri = UriComponentsBuilder.fromPath(RESTAURANT_API_URI).queryParam("longitude", "30.31").build().toUri();

        assertEquals(RESTAURANT_API_URI + "?longitude=30.31", uri.toString());

        MockHttpServletResponse response = mvc.perform(get(uri.toString()).accept(APPLICATION_JSON_UTF8))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}
