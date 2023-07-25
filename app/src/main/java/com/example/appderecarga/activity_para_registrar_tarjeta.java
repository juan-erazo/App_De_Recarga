package com.example.appderecarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_para_registrar_tarjeta extends AppCompatActivity {

    private EditText num_tarjeta;
    private EditText fecha_tarjeta;
    private EditText num_csv;
    private Button cancelar;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_registrar_tarjeta);

        cancelar = (Button) findViewById(R.id.btn_cancelar);
        registrar = (Button) findViewById(R.id.btn_registrar);

        num_tarjeta = (EditText) findViewById(R.id.txt_numTarjeta);
        fecha_tarjeta = (EditText) findViewById(R.id.txt_fechaTarjeta);
        num_csv = (EditText) findViewById(R.id.txt_numCSV);

        cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //codigo para el boton cancelar
                Intent ant = new Intent(activity_para_registrar_tarjeta.this, activity_operacion_recarga.class);
                startActivity(ant);

            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n_tarjeta = num_tarjeta.getText().toString();
                String f_tarjeta = num_tarjeta.getText().toString();
                String n_csv = num_csv.getText().toString();

                //codigo para el boton registrar
                String validacion = validarCampos(n_tarjeta, f_tarjeta, n_csv);

                if (validacion.equals("")){
                    //se puede regitrar ya que no falta ningun campo



                }else {
                    //mensaje en caso falte algun campo
                    Toast.makeText(activity_para_registrar_tarjeta.this, "" + validacion, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public String validarCampos(String numTar, String fechaTar, String numCSV) {

        if (numTar.equals("")){
            return "Ingrese su numero de Tarjeta";
        } else if(fechaTar.equals("")) {
            return "Ingrese la fecha de su Tarjeta";
        } else if(numCSV.equals("")) {
            return "Ingrese el numero CSV de su Tarjeta";
        } else {
            return "";
        }

    }
}