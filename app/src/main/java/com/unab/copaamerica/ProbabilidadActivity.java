package com.unab.copaamerica;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unab.copaamerica.adapter.ProbalidadAdapter;
import com.unab.copaamerica.constants.Cons;
import com.unab.copaamerica.model.Probabilidad;

import java.util.ArrayList;

public class ProbabilidadActivity extends AppCompatActivity {

    //region Declaration
    DatabaseReference clasificadosDB;
    ListView ListProbabilidad;
    ProbalidadAdapter adapter;
    public ArrayList<Probabilidad> ArrProb ;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probabilidad);
        this.setTitle("Probabilidad de Clasificacion");
        clasificadosDB = FirebaseDatabase.getInstance().getReference().child(Cons.FB_PROBABILITIES);
        clasificadosDB.orderByChild("probabilidad");
        //createCountryView(this);
        ListProbabilidad = (ListView) findViewById(R.id.list_probalidadd) ;

        ArrProb = new ArrayList<>();
        adapter = new ProbalidadAdapter(this,ArrProb);
        ListProbabilidad.setAdapter(adapter);
        iniView();

    }

    public void iniView() {
        clasificadosDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrProb.clear();

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    String bandera =        data.child("bandera").getValue().toString();
                    String nombre =         data.child("nombre").getValue().toString();
                    String probabilidad =   data.child("probabilidad").getValue().toString();

                    ArrProb.add(new Probabilidad(bandera,nombre,Float.parseFloat(probabilidad)));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
