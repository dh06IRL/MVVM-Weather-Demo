package com.cartoaware.mvvm.bindings;

import android.location.Location;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.cartoaware.mvvm.model.weather.Weather;
import com.cartoaware.mvvm.utils.Constants;
import com.cartoaware.mvvm.utils.LocationUtils;
import com.davidhodge92.weather.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class BindingAdapterUtils {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .transition(DrawableTransitionOptions.withCrossFade(900))
                .thumbnail(0.1f)
                .into(imageView);
    }

    @BindingAdapter("weatherImgUrl")
    public static void loadWeatherImage(ImageView imageView, List<Weather> weathers){
        if(weathers != null) {
            if (weathers.size() > 0) {
                String url = Constants.WEATHER_ICON_BASE_URL + weathers.get(0).icon + "@2x.png";
                Glide.with(imageView.getContext())
                        .load(url)
                        .transition(DrawableTransitionOptions.withCrossFade(900))
                        .into(imageView);
            }
        }
    }

    @BindingAdapter("weatherTime")
    public static void displayWeatherTime(TextView textView, Long time){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("ha MM/dd", cal).toString();
        textView.setText(date);
    }

    @BindingAdapter("weatherTimeDay")
    public static void displayWeatherTimeDay(TextView textView, Long time){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("MM/dd", cal).toString();
        textView.setText(date);
    }

    @BindingAdapter("weatherDesc")
    public static void displayWeatherDesc(TextView textView, List<Weather> weathers){
        if(weathers != null) {
            if (weathers.size() > 0) {
                String display = "";
                if(weathers.get(0).main != null){
                    display = display + weathers.get(0).main;
                }else{
                    display = display + "-";
                }
                if(weathers.get(0).description != null){
                    display = display + " : " + weathers.get(0).description;
                }
                textView.setText(display);
            }
        }
    }

    @BindingAdapter({"imgLat", "imgLon"})
    public static void displayMapImage(ImageView imageView, Double lat, Double lon){
        if(lat != null && lon != null) {
            Glide.with(imageView.getContext())
                    .load(String.format(Constants.BASE_MAP_URL, lon, lat))
                    .transition(DrawableTransitionOptions.withCrossFade(900))
                    .transform(new RoundedCorners((int)imageView.getContext().getResources().getDimension(R.dimen.img_corner_radius)))
                    .thumbnail(0.1f)
                    .into(imageView);
        }
    }

    @BindingAdapter({"lat", "lon"})
    public static void calculateDistance(TextView textView, Double lat, Double lon){
        if(lat != null && lon != null) {
            Location rLocation = LocationUtils.locationBuilder(lat, lon);
            Location uLocation = LocationUtils.locationBuilder(Constants.DEFAULT_LAT, Constants.DEFAULT_LON);
            //Just a quick drop in, would further optimize + setup for translation support
            textView.setText(String.format("%.2f", rLocation.distanceTo(uLocation) * 0.00062137119) + "mi Away");
        }else{
            textView.setText("-");
        }
    }
}
