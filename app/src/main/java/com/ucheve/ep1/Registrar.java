package com.ucheve.ep1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Registrar extends AppCompatActivity {

    EditText txtNomApe;
    EditText txtN1;
    EditText txtN2;
    EditText txtN3;
    EditText txtN4;
    Spinner spnCurso;
    Button btnRegistrar;
    Button btnLimpiar;
    ArrayList<EditText> txts;
    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtNomApe = findViewById(R.id.txtApeNom);
        txtN1 = findViewById(R.id.txtN1);
        txtN2 = findViewById(R.id.txtN2);
        txtN3 = findViewById(R.id.txtN3);
        txtN4 = findViewById(R.id.txtN4);
        spnCurso = findViewById(R.id.spnCurso);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnRegistrar = findViewById(R.id.btnRegistrar2);
        data = new JSONObject();

        txts = new ArrayList<>();
        txts.add(txtNomApe);
        txts.add(txtN1);
        txts.add(txtN2);
        txts.add(txtN3);
        txts.add(txtN4);

        btnRegistrar.setOnClickListener(v -> {
            try {
                registrar();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "SÓLO SE ACEPTAN NÚMEROS. Ej: 10.5", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.i("ep_1", e.toString());
            }
        });

        btnLimpiar.setOnClickListener(v -> {
            clear();
        });
    }

    private void registrar() {

        ArrayList<Double> notas = new ArrayList<>();
        Double nota_eliminada;

        for (EditText t : txts) {
            if (t != txtNomApe) {
                notas.add(Double.parseDouble(t.getText().toString()));
            }
        }

      for (Double n : notas) {
          if (n > 20) {
              Toast.makeText(this, "NÚMERO INVÁLIDO", Toast.LENGTH_SHORT).show();
              return;
          }
      }

        try {
            data.put("n1", notas.get(0));
            data.put("n2", notas.get(1));
            data.put("n3", notas.get(2));
            data.put("n4", notas.get(3));
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        Collections.sort(notas);
        nota_eliminada = notas.get(0);

        try {
            data.put("nota_eliminada", nota_eliminada);
            data.put("nombre", txtNomApe.getText().toString());
            data.put("curso", spnCurso.getSelectedItem().toString());
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        Intent it = new Intent(this, Reporte.class);
        it.putExtra("data", data.toString());
        startActivity(it);
    }

    private void clear() {
        for (EditText t : txts) {
            t.setText("");
        }
    }
}