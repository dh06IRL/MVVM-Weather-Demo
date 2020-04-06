package com.cartoaware.mvvm.utils;

import android.location.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationUtilsTest {

    private Location location;

    @Before
    public void setUp() throws Exception {
        location = LocationUtils.locationBuilder(37.422740, -122.139956);

        System.out.println("Test Setup");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Test Complete");
    }

    @Test
    public void locationBuilder() {
        assertNotEquals(null, location.getLatitude());
        assertNotEquals(0.0, location.getLatitude());
        assertEquals("builder", location.getProvider());
    }
}