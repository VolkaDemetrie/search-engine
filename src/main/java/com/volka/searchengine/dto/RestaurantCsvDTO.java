package com.volka.searchengine.dto;

import com.opencsv.bean.CsvBindByPosition;

public class RestaurantCsvDTO {
    // 음식점 명
    @CsvBindByPosition(position = 0)
    private String restaurantName;
    // 카테고리 1
    @CsvBindByPosition(position = 1)
    private String category1;
    // 카테고리 2
    @CsvBindByPosition(position = 2)
    private String category2;
    // 카테고리 3
    @CsvBindByPosition(position = 3)
    private String category3;
    // 지역명
    @CsvBindByPosition(position = 4)
    private String region;
    // 시군구명
    @CsvBindByPosition(position = 5)
    private String city;
    // 개요
    @CsvBindByPosition(position = 6)
    private String description;

    public RestaurantCsvDTO() {}

    public RestaurantCsvDTO(String restaurantName, String category1, String category2, String category3, String region, String city, String description) {
        this.restaurantName = restaurantName;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.region = region;
        this.city = city;
        this.description = description;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RestaurantInfo{" +
                "restaurantName='" + restaurantName + '\'' +
                ", category1='" + category1 + '\'' +
                ", category2='" + category2 + '\'' +
                ", category3='" + category3 + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                "}\n";
    }
}
