package com.example.appderecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class activity_operacion_recarga extends AppCompatActivity {

    private Spinner spinner_selecione_empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacion_recarga);

        spinner_selecione_empresa = (Spinner) findViewById(R.id.Spiner_empresa);

        String[] empresas = {"Claro, Movistar, Entel, Bitel"};

        ArrayAdapter <String> empresa_seleccion = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empresas);
        spinner_selecione_empresa.setAdapter(empresa_seleccion);

    }
}