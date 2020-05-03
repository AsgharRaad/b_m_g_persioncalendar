package com.shamim.farsi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamim.farsi.Constants;
import com.shamim.farsi.R;
import com.shamim.farsi.entity.CityEntity;
import com.shamim.farsi.util.Utils;
import com.shamim.farsi.view.preferences.LocationPreferenceDialog;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private String locale;
    private List<CityEntity> cities;
    private Utils utils;
    private LocationPreferenceDialog locationPreferenceDialog;
    private LayoutInflater layoutInflater;

    public LocationAdapter(LocationPreferenceDialog locationPreferenceDialog) {
        Context context = locationPreferenceDialog.getContext();
        utils = Utils.getInstance(locationPreferenceDialog.getContext());
        this.layoutInflater = LayoutInflater.from(context);
        this.locationPreferenceDialog = locationPreferenceDialog;
        this.cities = utils.getAllCities(true);
        this.locale = utils.getAppLanguage();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvCountry;
        private TextView tvCity;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvCountry = (TextView) itemView.findViewById(R.id.tvCountry);

            utils.setFont(tvCity);
            utils.setFont(tvCountry);
        }

        @Override
        public void onClick(View view) {
            locationPreferenceDialog.selectItem(cities.get(getAdapterPosition()).getKey());
        }
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item_city_name, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCity.setText(locale.equals(Constants.LANG_EN)
                ? cities.get(position).getEn()
                : utils.shape(cities.get(position).getFa()));

        holder.tvCountry.setText(locale.equals(Constants.LANG_EN)
                ? cities.get(position).getCountryEn()
                : utils.shape(cities.get(position).getCountryFa()));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}