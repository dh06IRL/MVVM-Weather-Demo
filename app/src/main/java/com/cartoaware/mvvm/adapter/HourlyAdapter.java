package com.cartoaware.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cartoaware.mvvm.model.weather.Hourly;
import com.davidhodge92.weather.databinding.ViewHourItemBinding;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder> {

    private List<Hourly> hourlyItems;

    public HourlyAdapter(List<Hourly> hourlyList) {
        this.hourlyItems = hourlyList;
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHourItemBinding itemBinding = ViewHourItemBinding.inflate(layoutInflater, parent, false);
        return new HourlyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        final Hourly restaurant = hourlyItems.get(position);
        holder.bindHourly(restaurant);
    }

    @Override
    public int getItemCount() {
        return hourlyItems != null ? hourlyItems.size() : 0;
    }

    class HourlyViewHolder extends RecyclerView.ViewHolder {
        private ViewHourItemBinding restaurantItemBinding;

        HourlyViewHolder(ViewHourItemBinding binding) {
            super(binding.getRoot());
            this.restaurantItemBinding = binding;
        }

        void bindHourly(Hourly hourly) {
            restaurantItemBinding.setHourly(hourly);
            restaurantItemBinding.executePendingBindings();
        }
    }
}
