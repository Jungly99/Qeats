package com.crio.qeats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

// TODO: CRIO_TASK_MODULE_SERIALIZATION
// Implement Restaurant class.
// Complete the class such that it produces the following JSON during serialization.
// {
// "restaurantId": "10",
// "name": "A2B",
// "city": "Hsr Layout",
// "imageUrl": "www.google.com",
// "latitude": 20.027,
// "longitude": 30.0,
// "opensAt": "18:00",
// "closesAt": "23:00",
// "attributes": [
// "Tamil",
// "South Indian"
// ]
// }

public class Restaurant {

    @JsonProperty("restaurantId")
    private String restaurantId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("city")
    private String city;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("opensAt")
    private String opensAt;

    @JsonProperty("closesAt")
    private String closesAt;

    @JsonProperty("attributes")
    private List<String> attributes;

    // Default constructor
    public Restaurant() {
    }

    // Parameterized constructor
    public Restaurant(String restaurantId, String name, String city, String imageUrl, double latitude, double longitude, String opensAt, String closesAt, List<String> attributes) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.city = city;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.attributes = attributes;
    }

    // Getters and setters
    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(String opensAt) {
        this.opensAt = opensAt;
    }

    public String getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(String closesAt) {
        this.closesAt = closesAt;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    // Main method for testing
    public static void main(String[] args) throws IOException {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId("10");
        restaurant.setName("A2B");
        restaurant.setCity("Hsr Layout");
        restaurant.setImageUrl("www.google.com");
        restaurant.setLatitude(20.027);
        restaurant.setLongitude(30.0);
        restaurant.setOpensAt("18:00");
        restaurant.setClosesAt("23:00");
        restaurant.setAttributes(List.of("Tamil", "South Indian"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(restaurant);
        System.out.println(json);
    }
}
