package com.unab.copaamerica.model;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unab.copaamerica.R;
import com.unab.copaamerica.constants.Cons;

import java.util.ArrayList;

public class graficos extends AppCompatActivity {

    DatabaseReference clasificadosDB;
    BarChart chart;

    ArrayList<String> xAxis;
    ArrayList<BarDataSet> dataSets;
    BarData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        this.setTitle(R.string.probabilidad);

        clasificadosDB = FirebaseDatabase.getInstance().getReference().child(Cons.FB_PROBABILITIES);

        chart = (BarChart) findViewById(R.id.chart);

        dataSets = null;
        xAxis = new ArrayList<>();

        getXAxisValues();
    }

    private void getXAxisValues() {
        ArrayList<BarEntry> valueSet = new ArrayList<>();

        clasificadosDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                valueSet.clear();
                xAxis.clear();

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    String bandera = data.child("bandera").getValue().toString();
                    xAxis.add(bandera);

                    String javier = data.child("probabilidad").getValue().toString();
                    Float pro = Float.parseFloat(javier);
                    BarEntry valor = new BarEntry(pro, Integer.parseInt(data.getKey())); // QAT
                    valueSet.add(valor);

                }

                BarDataSet barDataSet1 = new BarDataSet(valueSet, "ProbabilidadActivity de Avanzar");
                barDataSet1.setColor(Color.rgb(0, 170, 228));
                dataSets = new ArrayList<>();
                dataSets.add(barDataSet1);

                initChart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  void initChart() {
        data = new BarData(xAxis, dataSets);
        chart.setData(data);
        chart.setDescription("País");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }
}
