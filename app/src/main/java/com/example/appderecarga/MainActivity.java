package com.example.appderecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegis = (Button) findViewById(R.id.btnRegis);

        Siguiente();
    }

    /* ------------------------------------ BOTON REGISTRAR ------------------------------------- */
    public void Siguiente(){
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = new Intent(MainActivity.this, activity_registrar_usuario.class);
                startActivity(sig);
            }
        });
    }
}