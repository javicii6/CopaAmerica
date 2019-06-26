package com.unab.copaamerica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unab.copaamerica.adapter.CountryAdapter;
import com.unab.copaamerica.constants.Cons;
import com.unab.copaamerica.model.Clasificado;
import com.unab.copaamerica.model.Country;
import com.unab.copaamerica.model.graficos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //region Declaration
    DatabaseReference clasificadosDB;
    ListView ListCountry;
    CountryAdapter adapter;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_list);
        createCountryView(this);
    }

    public void createCountryView(Context context) {

        SharedPreferences prefs =
                getSharedPreferences(Cons.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String paisesJson = prefs.getString(Cons.SP_COUNTRIES, "");

        Gson gson = new Gson();
        TypeToken<ArrayList<Country>> token = new TypeToken<ArrayList<Country>>() {};
        ArrayList<Country> listaPaises = gson.fromJson(paisesJson, token.getType());

        adapter = new CountryAdapter(context, listaPaises);
        ListCountry = (ListView) findViewById(R.id.match_list);

        ListCountry.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ListCountry.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.guardar:
                clasificadosDB = FirebaseDatabase.getInstance().getReference().child(Cons.FB_PROBABILITIES);
                clasificadosDB.setValue(null);
                int count = 0;
                for(int i=0; i<adapter.getCount();i++){
                    Country data = adapter.getItem(i);
                    if(data.isChecked())
                    {
                        Clasificado clasificado = new Clasificado(data.getBandera(),data.getNombre(),data.getProbabilidad().replace(",", "."));
                        clasificadosDB.child(String.valueOf(count)).setValue(clasificado);
                        count += 1;
                    }
                }
                return true;

            case R.id.graficos:
                startActivity(new Intent(MainActivity.this, graficos.class));
                return true;

            case R.id.estadisticas:
                startActivity(new Intent(MainActivity.this, ProbabilidadActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
