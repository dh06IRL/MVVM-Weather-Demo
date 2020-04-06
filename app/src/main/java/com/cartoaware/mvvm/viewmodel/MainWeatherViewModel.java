package com.cartoaware.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cartoaware.mvvm.data.geo.GeoRepository;
import com.cartoaware.mvvm.data.location.LocationRepository;
import com.cartoaware.mvvm.data.weather.WeatherRepository;
import com.cartoaware.mvvm.model.geo.GeoResponse;
import com.cartoaware.mvvm.model.weather.WeatherResponse;

public class MainWeatherViewModel extends ViewModel  {

    private MutableLiveData<WeatherResponse> restaurantLiveData = new MutableLiveData<>();
    private MutableLiveData<GeoResponse> geoLiveData = new MutableLiveData<>();
    private WeatherRepository weatherRepository;
    private GeoRepository geoRepository;

    public MainWeatherViewModel(){
        this.weatherRepository = WeatherRepository.getInstance();
        this.geoRepository = GeoRepository.getInstance();
    }

    public MainWeatherViewModel(WeatherRepository weatherRepositoryPassed){
        this.weatherRepository = weatherRepositoryPassed;
    }

    public void fetchLocalData(Double lat, Double lon){
        restaurantLiveData = weatherRepository.getLocalWeatherData(lat, lon);
        geoLiveData = geoRepository.getLocalGeoData(lat, lon);
    }

    public LiveData<WeatherResponse> getLocalWeatherData() {
        return restaurantLiveData;
    }

    public LiveData<GeoResponse> getLocalGeoData() {
        return geoLiveData;
    }

    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }

}

