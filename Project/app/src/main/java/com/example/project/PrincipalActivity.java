package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    Button btnDatos;
    TextView tvNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnDatos = (Button) findViewById(R.id.btnDatos);

        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatos();
            }
        });

        // para identificar que usuario se encuentra activo y ver si los sharedpreferences realiza los cambios adecuadamente
        // recuperamos el dato => lo igualamos a una variable string => enviamos a un textView
        SharedPreferences sp1 = getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        String nombre = sp1.getString("nombre","NO ENCONTRADO");
        int id = sp1.getInt("id",0);

        tvNameUser = (TextView) findViewById(R.id.textViewUserName);
        tvNameUser.setText("Nombre del usuario:  "+nombre+" y mi id es "+id);
        //tvNameUser.setText("Nombre del usuario:  "+nombre);
    }

    private void mostrarDatos() {
        Intent intent = new Intent(this,InformacionActivity.class);
        startActivity(intent);
    }

}