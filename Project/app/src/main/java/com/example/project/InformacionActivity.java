package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InformacionActivity extends AppCompatActivity {


    TextView tvPadecimiento, tvMedicamento, tvIndicaciones, tvNombreClinica, tvNotas,tvNombreC;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        SharedPreferences sp1 = getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        String nombre = sp1.getString("nombre","NO ENCONTRADO");
        String apellido = sp1.getString("apellidos","NO ENCONTRADO");

        tvNombreC = (TextView) findViewById(R.id.tvNombreC);
        tvNombreC.setText(nombre+" "+apellido);


        tvPadecimiento = (TextView) findViewById(R.id.tvPadecimiento);
        tvMedicamento = (TextView) findViewById(R.id.tvMedicamento);
        tvIndicaciones = (TextView) findViewById(R.id.tvIndicaciones);
        tvNombreClinica = (TextView) findViewById(R.id.tvClinica);
        tvNotas = (TextView) findViewById(R.id.tvNotas);

        // para el boton de back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // configuracion del menu a de tres puntos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    // utlizado para la seleccion de los items en el menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalir:
                Toast.makeText(getApplicationContext(),"SALIR",
                        Toast.LENGTH_LONG).show();
                finishAffinity();
                //return true;
                break;
            case R.id.itemCerrarS:
                Toast.makeText(getApplicationContext(),"CERRAR SESION",
                        Toast.LENGTH_LONG).show();
                cerrarSesion();
                //return true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // metodo utilizado por un item del menu
    private void cerrarSesion() {
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
    }



    private void searchData(){
        SharedPreferences prefer1 = getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        int id = prefer1.getInt("id",0);
        String miId = Integer.toString(id);
        String URL="http://192.168.1.80/projectws/search_data.php?id_user="+miId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        tvPadecimiento.setText(jsonObject.getString("suffering"));
                        tvMedicamento.setText(jsonObject.getString("medicine"));
                        tvIndicaciones.setText(jsonObject.getString("indications"));
                        tvNombreClinica.setText(jsonObject.getString("name_clinic"));
                        tvNotas.setText(jsonObject.getString("note"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR EN LA CONEXION "+error,
                        Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}