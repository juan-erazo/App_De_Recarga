package com.example.appderecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class activity_menu_principal extends AppCompatActivity {

    private Button registrar_tarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        registrar_tarjeta = (Button) findViewById(R.id.btn_RegistrarTarjeta);

        registrar_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = new Intent(activity_menu_principal.this, activity_para_registrar_tarjeta.class);
                startActivity(sig);
            }
        });

    }

}