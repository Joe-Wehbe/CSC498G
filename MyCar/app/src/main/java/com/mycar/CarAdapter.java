package com.mycar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CarAdapter extends ArrayAdapter<Car> {

    private Context mContext;
    private int mResource;

    public CarAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Car> objects){
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView brand = convertView.findViewById(R.id.vehicleBrand);
        TextView plate = convertView.findViewById(R.id.model);

        brand.setText(getItem(position).getBrand());
        plate.setText(getItem(position).getPlate());

        return convertView;
    }
}
