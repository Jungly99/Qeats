package com.crio.qeats.controller;

import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(RestaurantController.RESTAURANT_API_ENDPOINT)
@Log4j2
public class RestaurantController {

  public static final String RESTAURANT_API_ENDPOINT = "/qeats/v1";
  public static final String RESTAURANTS_API = "/restaurants";
  public static final String MENU_API = "/menu";

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping(RESTAURANTS_API)
  public ResponseEntity<GetRestaurantsResponse> getRestaurants(
       @RequestParam GetRestaurantsRequest getRestaurantsRequest) {
    log.info("getRestaurants called with {}", getRestaurantsRequest);
    GetRestaurantsResponse getRestaurantsResponse;
    getRestaurantsResponse = restaurantService
          .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
    log.info("getRestaurants returned {}", getRestaurantsResponse);
    return ResponseEntity.ok().body(getRestaurantsResponse);
  }

  @GetMapping(MENU_API)
  public ResponseEntity<GetMenuResponse> getMenu(
       @RequestParam("restaurantId") String restaurantId) {
    // Implementation of getMenu method
  }

  // Other API endpoints can be implemented similarly
}
