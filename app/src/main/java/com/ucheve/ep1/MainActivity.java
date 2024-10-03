package com.ucheve.ep1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRegistrar;
    Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnListar = findViewById(R.id.btnListar);

        btnRegistrar.setOnClickListener(v -> {
            goRegistar();
        });

        btnListar.setOnClickListener(v -> {
            goListar();
        });
    }

    private void goRegistar() {
        Intent it = new Intent(this, Registrar.class);
        startActivity(it);
    }

    private void goListar() {
        Intent it = new Intent(this, ListCursos.class);
        startActivity(it);
    }
}