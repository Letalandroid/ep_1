package com.ucheve.ep1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListCursos extends AppCompatActivity {

    TextView lblCursos;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cursos);

        lblCursos = findViewById(R.id.lblCursos);
        lblCursos.setText("Programación Móviles\n" +
                        "Business Intelligence\n" +
                        "Investigación Científica\n" +
                        "Comercio Electrónico\n");

        btnReturn = findViewById(R.id.btnReturn2);

        btnReturn.setOnClickListener(v -> {
            finish();
        });
    }
}