package com.unab.copaamerica.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unab.copaamerica.R;
import com.unab.copaamerica.model.Probabilidad;

import java.util.ArrayList;

public class ProbalidadAdapter extends BaseAdapter {

    private Context context;
    public static ArrayList<Probabilidad> data;

    public ProbalidadAdapter(Context context, ArrayList<Probabilidad> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Probabilidad getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        final ProbalidadAdapter.Probabilidadholder holder;

        if(item == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            item = inflater.inflate(R.layout.probalidad, null);

            holder = new ProbalidadAdapter.Probabilidadholder();
            holder.bandeda = item.findViewById(R.id.txt_bandera);
            holder.nombre = item.findViewById(R.id.txt_nombre);
            holder.probabilidad = item.findViewById(R.id.txt_probalidad);

            item.setTag(holder);

        } else {
            holder = (ProbalidadAdapter.Probabilidadholder) item.getTag();
        }

        Probabilidad currentProbability = data.get(position);
        holder.bandeda.setText(currentProbability.getBandera());
        holder.nombre.setText(currentProbability.getNombre());
        holder.probabilidad.setText(String.valueOf(currentProbability.getProbabilidad()));

        return (item);
    }

    private class Probabilidadholder {
        TextView bandeda;
        TextView nombre;
        TextView probabilidad;
    }

}
