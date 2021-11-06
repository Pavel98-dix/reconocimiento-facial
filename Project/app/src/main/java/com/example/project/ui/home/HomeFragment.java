package com.example.project.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.project.R;
import com.google.android.material.snackbar.Snackbar;
import static androidx.navigation.Navigation.findNavController;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sp1 = this.getActivity().getSharedPreferences("MisPreferencias",
                Context.MODE_PRIVATE);
        String nombre = sp1.getString("nombre","NO ENCONTRADO");
        //int id = sp1.getInt("id",0);


        TextView tvNameUser = (TextView) root.findViewById(R.id.textViewUserName);
        tvNameUser.setText("Nombre del usuario:  \n"+nombre);


        final Button btnDatos = (Button) root.findViewById(R.id.btnDatos);
        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.pantallaMostrarDatos);
            }
        });

        return root;
    }
}