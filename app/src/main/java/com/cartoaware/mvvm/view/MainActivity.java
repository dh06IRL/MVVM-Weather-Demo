package com.cartoaware.mvvm.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.cartoaware.mvvm.adapter.DayAdapter;
import com.cartoaware.mvvm.adapter.HourlyAdapter;
import com.cartoaware.mvvm.data.location.LocationRepository;
import com.cartoaware.mvvm.model.weather.Daily;
import com.cartoaware.mvvm.model.weather.Hourly;
import com.cartoaware.mvvm.utils.Constants;
import com.cartoaware.mvvm.viewmodel.MainWeatherViewModel;
import com.davidhodge92.weather.R;
import com.davidhodge92.weather.databinding.MainActivityBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainWeatherViewModel mainWeatherViewModel;
    private MainActivityBinding binding;

    private ArrayList<Hourly> hourlyArrayList = new ArrayList<>();
    private ArrayList<Daily> dayArrayList = new ArrayList<>();

    private HourlyAdapter hourlyAdapter;
    private DayAdapter dayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.main_title));

        mainWeatherViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainWeatherViewModel.class);

        binding.hourlyRecyclerview.setHasFixedSize(true);
        binding.daysRecyclerview.setHasFixedSize(true);
        hourlyAdapter = new HourlyAdapter(hourlyArrayList);
        dayAdapter = new DayAdapter(dayArrayList);
        binding.hourlyRecyclerview.setAdapter(hourlyAdapter);
        binding.daysRecyclerview.setAdapter(dayAdapter);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, Constants.PERM_REQ_CODE);
        } else {
            setupLocationUpdates();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.PERM_REQ_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setupLocationUpdates();
            }
        }
    }

    private void setupLocationUpdates() {
        LocationRepository.getInstance(getApplicationContext(), true).observe(this, location -> {
            if(location != null) {
                mainWeatherViewModel.fetchLocalData(location.getLatitude(), location.getLongitude());

                mainWeatherViewModel.getLocalWeatherData().observe(this, response -> {
                    if (response != null) {
                        binding.setWeather(response);

                        hourlyArrayList.addAll(response.hourly);
                        hourlyAdapter.notifyDataSetChanged();

                        dayArrayList.addAll(response.daily);
                        dayAdapter.notifyDataSetChanged();
                        binding.weatherLoading.setVisibility(View.GONE);
                    }
                });
                mainWeatherViewModel.getLocalGeoData().observe(this, geoResponse -> {
                    if (geoResponse != null) {
                        getSupportActionBar().setTitle(geoResponse.displayName);
                    }
                });
            }
        });
    }
}
