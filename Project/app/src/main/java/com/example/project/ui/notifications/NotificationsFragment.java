package com.example.project.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.MainActivity;
import com.example.project.R;
import com.google.android.material.snackbar.Snackbar;

public class NotificationsFragment extends Fragment {

    TextView tvContacto,tvCerrarSesion,tvSalir;
    Snackbar snackbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tvContacto = (TextView) root.findViewById(R.id.tvContactanos);
        tvContacto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onClick(View view) {
                //snackbarMet("Contacto",root);
            }
        });

        tvCerrarSesion = (TextView) root.findViewById(R.id.tvCerrarSesion);
        tvCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });

        tvSalir = (TextView) root.findViewById(R.id.tvSalir);
        tvSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"ADIOS", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
            }
        });

        return root;
    }

    private void cerrarSesion() {
        SharedPreferences sp2 = this.getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp2.edit();
        editor.putBoolean("ingreso",false);
        editor.commit();

        Intent intent2 = new Intent(getContext(), MainActivity.class);
        startActivity(intent2);
    }

    @SuppressLint("ResourceType")
    private void snackbarMet(String string, View root) {
        snackbar = Snackbar.make(root, string, Snackbar.LENGTH_INDEFINITE);
        snackbar.setDuration(2000);
        snackbar.setText(R.color.colorLetter);
        //snackbar.setBackgroundTint(R.color.colorDark);
        View snackBarView = snackbar.getView();
        //BACKGROUND
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorDark));
        // colocar arriba
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)snackBarView.getLayoutParams();
        params.gravity = Gravity.TOP;
        snackBarView.setLayoutParams(params);
        // mostrar
        snackbar.show();
    }

}