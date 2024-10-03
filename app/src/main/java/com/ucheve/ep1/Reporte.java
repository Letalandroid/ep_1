package com.ucheve.ep1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reporte extends AppCompatActivity {

    JSONObject data;
    EditText txtResult;
    String nombres;
    String curso;
    Button btnHome;
    Button btnReturn;
    Double n1;
    Double n2;
    Double n3;
    Double n4;
    Double nota_eliminada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        txtResult = findViewById(R.id.txtResult);
        btnHome = findViewById(R.id.btnHome);
        btnReturn = findViewById(R.id.btnReturn);
        try {
            data = new JSONObject(getIntent().getStringExtra("data"));
            nombres = data.getString("nombre");
            curso = data.getString("curso");
            n1 = data.getDouble("n1");
            n2 = data.getDouble("n2");
            n3 = data.getDouble("n3");
            n4 = data.getDouble("n4");
            nota_eliminada = data.getDouble("nota_eliminada");
        } catch (JSONException e) {
            Log.i("ep_1", e.toString());
        }

        String result = String.format("Estudiante %s\n" +
                        "Curso: %s\n" +
                        "Nota 1: %s\n" +
                        "Nota 2: %s\n" +
                        "Nota 3: %s\n" +
                        "Nota 4: %s\n" +
                        "Nota eliminada: %s\n" +
                        "Promedio: %s\n" +
                        "Condicion: %s\n",
                nombres, curso, n1, n2, n3, n4, nota_eliminada, String.format("%.2f", getPromedio()),
                getPromedio() >= 10.5 ? "APROBADO" : "DESAPROBADO");

        txtResult.setText(result);

        btnHome.setOnClickListener(v -> {
            goHome();
        });

        btnReturn.setOnClickListener(v -> {
            goBack();
        });
    }

    private void goHome() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    private void goBack() {
        Intent it = new Intent(this, Registrar.class);
        startActivity(it);
    }

    private float getPromedio() {
        try {
            float n1 = (float) data.getDouble("n1");
            float n2 = (float) data.getDouble("n2");
            float n3 = (float) data.getDouble("n3");
            float n4 = (float) data.getDouble("n4");
            float res = 0;

            ArrayList<Float> numbers = new ArrayList<>();
            numbers.add(n1);
            numbers.add(n2);
            numbers.add(n3);
            numbers.add(n4);

            for (Float n : numbers) {
                if (n != data.getDouble("nota_eliminada")) {
                    res += n;
                }
            }

            return (res/3);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}