package com.cartoaware.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cartoaware.mvvm.model.weather.Daily;
import com.davidhodge92.weather.databinding.ViewDayItemBinding;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DailyViewHolder> {

    private List<Daily> dailyItems;

    public DayAdapter(List<Daily> hourlyList) {
        this.dailyItems = hourlyList;
    }

    @NonNull
    @Override
    public DayAdapter.DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDayItemBinding itemBinding = ViewDayItemBinding.inflate(layoutInflater, parent, false);
        return new DayAdapter.DailyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.DailyViewHolder holder, int position) {
        final Daily daily = dailyItems.get(position);
        holder.bindDaily(daily);
    }

    @Override
    public int getItemCount() {
        return dailyItems != null ? dailyItems.size() : 0;
    }

    class DailyViewHolder extends RecyclerView.ViewHolder {
        private ViewDayItemBinding restaurantItemBinding;

        DailyViewHolder(ViewDayItemBinding binding) {
            super(binding.getRoot());
            this.restaurantItemBinding = binding;
        }

        void bindDaily(Daily daily) {
            restaurantItemBinding.setDay(daily);
            restaurantItemBinding.executePendingBindings();
        }
    }
}
