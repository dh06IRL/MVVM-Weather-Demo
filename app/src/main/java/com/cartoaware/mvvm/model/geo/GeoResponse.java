package com.cartoaware.mvvm.model.geo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GeoResponse {
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("osm_type")
    @Expose
    public String osmType;
    @SerializedName("osm_id")
    @Expose
    public String osmId;
    @SerializedName("licence")
    @Expose
    public String licence;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lon")
    @Expose
    public String lon;
    @SerializedName("display_name")
    @Expose
    public String displayName;
    @SerializedName("boundingbox")
    @Expose
    public List<String> boundingbox = new ArrayList<>();
    @SerializedName("importance")
    @Expose
    public Double importance;
    @SerializedName("address")
    @Expose
    public Address address;
}
