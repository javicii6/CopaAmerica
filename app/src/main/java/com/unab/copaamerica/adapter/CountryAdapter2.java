package com.unab.copaamerica.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unab.copaamerica.R;
import com.unab.copaamerica.model.Country;

import java.util.ArrayList;

public class CountryAdapter2 extends BaseAdapter {

    private Context context;
    public static ArrayList<Country> data;

    public CountryAdapter2(Context context, ArrayList<Country> data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Country getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        final CountryAdapter2.CountryHolder holder;

        if(item == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            item = inflater.inflate(R.layout.activity_probabilidad, null);

            holder = new CountryHolder();
            holder.bandeda = item.findViewById(R.id.txt_bandera);
            holder.nombre = item.findViewById(R.id.txt_nombre);
            holder.probabilidad = item.findViewById(R.id.txt_nombre);

            item.setTag(holder);

        } else {
            holder = (CountryHolder) item.getTag();
        }

        Country currentCountry = data.get(position);
        holder.bandeda.setText(currentCountry.getBandera());
        holder.nombre.setText(currentCountry.getNombre());
        holder.probabilidad.setText(currentCountry.getNombre());


        return (item);
    }

    private class CountryHolder {
        TextView bandeda;
        TextView nombre;
        TextView probabilidad;
    }
}
