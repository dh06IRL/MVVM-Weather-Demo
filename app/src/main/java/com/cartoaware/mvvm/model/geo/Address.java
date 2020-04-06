package com.cartoaware.mvvm.model.geo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("house_number")
    @Expose
    public String houseNumber;
    @SerializedName("road")
    @Expose
    public String road;
    @SerializedName("neighbourhood")
    @Expose
    public String neighbourhood;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("county")
    @Expose
    public String county;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("postcode")
    @Expose
    public String postcode;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("country_code")
    @Expose
    public String countryCode;

}
