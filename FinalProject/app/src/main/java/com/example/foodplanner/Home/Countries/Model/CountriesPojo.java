package com.example.foodplanner.Home.Countries.Model;

public class CountriesPojo {
    private String name;
    private String imageUrl;

    public CountriesPojo(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getStrCountryName() {
        return name;
    }

    public String getStrCountryImage() {
        return imageUrl;
    }
}