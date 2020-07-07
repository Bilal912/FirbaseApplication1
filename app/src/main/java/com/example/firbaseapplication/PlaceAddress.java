package com.example.firbaseapplication;

public class PlaceAddress {

    private String address,country,city;

    public PlaceAddress(String address, String country, String city) {
        this.address = address;
        this.country = country;
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
