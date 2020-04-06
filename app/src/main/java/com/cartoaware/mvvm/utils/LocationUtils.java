package com.cartoaware.mvvm.utils;

import android.location.Location;

public class LocationUtils {

    public static Location locationBuilder(Double lat, Double lon){
        Location location = new Location("builder");
        location.setLatitude(lat);
        location.setLongitude(lon);
        return location;
    }
}
