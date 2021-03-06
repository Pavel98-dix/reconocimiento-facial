package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PantallaMostrarDatosAuxilio#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PantallaMostrarDatosAuxilio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PantallaMostrarDatosAuxilio.
     */
    // TODO: Rename and change types and number of parameters
    public static PantallaMostrarDatosAuxilio newInstance(String param1, String param2) {
        PantallaMostrarDatosAuxilio fragment = new PantallaMostrarDatosAuxilio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PantallaMostrarDatosAuxilio() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView tvPadecimiento, tvMedicamento, tvIndicaciones, tvNombreClinica, tvNotas,tvNombreC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pantalla_mostrar_datos_auxilio, container, false);

        tvNombreC = (TextView) root.findViewById(R.id.tvNombreC);
        tvPadecimiento = (TextView) root.findViewById(R.id.tvPadecimiento);
        tvMedicamento = (TextView) root.findViewById(R.id.tvMedicamento);
        tvIndicaciones = (TextView) root.findViewById(R.id.tvIndicaciones);
        tvNombreClinica = (TextView) root.findViewById(R.id.tvClinica);
        tvNotas = (TextView) root.findViewById(R.id.tvNotas);

        searchData();

        return root;
    }


    private void searchData(){
        SharedPreferences prefer1 = this.getActivity().getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        final int id = prefer1.getInt("id_help",0);
        final String miId = Integer.toString(id);
        String URL="http://192.168.1.80/projectws/search_data.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            tvNombreC.setText((jsonObject.getString("name"))+" "+jsonObject.getString("lastname"));
                            tvPadecimiento.setText(jsonObject.getString("suffering"));
                            tvMedicamento.setText(jsonObject.getString("medicine"));
                            tvIndicaciones.setText(jsonObject.getString("indications"));
                            tvNombreClinica.setText(jsonObject.getString("name_clinic"));
                            tvNotas.setText(jsonObject.getString("note"));
                        } catch (JSONException e) {
                            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            mostrarAlertDialog(e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "ERROR EN LA CONEXION "+error, Toast.LENGTH_LONG).show();
                mostrarAlertDialog("ERROR EN LA CONEXION "+error.toString());
            }
        }){
            protected Map<String, String> getParams(){
                Map<String,String> parametros = new HashMap<>();
                parametros.put("id",miId);
                return parametros;
            }
        };
        //requestQueue = Volley.newRequestQueue(getContext());
        //requestQueue.add(jsonArrayRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(stringRequest);
    }

    private void mostrarAlertDialog(String string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("FALLO - mostrar ayuda");
        builder.setMessage(string);
        builder.setPositiveButton("Aceptar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}