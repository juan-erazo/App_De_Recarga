package com.example.appderecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class activity_operacion_recarga extends AppCompatActivity {

    private Spinner spinner_selecione_empresa;
    private Button btn_Atr, btn_Recar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacion_recarga);

        btn_Atr = (Button) findViewById(R.id.btnAtr);
        btn_Recar = (Button) findViewById(R.id.btnRecargar);
        spinner_selecione_empresa = (Spinner) findViewById(R.id.Spiner_empresa);

        String[] empresas = {"Claro, Movistar, Entel, Bitel"};

        ArrayAdapter <String> empresa_seleccion = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empresas);
        spinner_selecione_empresa.setAdapter(empresa_seleccion);

        Atras();
        Recargar();
    }

    /* -------------------------------------- BOTON ATRAS --------------------------------------- */
    private void Atras(){
        btn_Atr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atr = new Intent(activity_operacion_recarga.this, MainActivity.class);
                startActivity(atr);
            }
        });
    }

    /* ------------------------------------- BOTON RECARGAR ------------------------------------- */
    private void Recargar(){
        btn_Recar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = new Intent(activity_operacion_recarga.this, activity_para_registrar_tarjeta.class);
                startActivity(sig);
            }
        });
    }
}