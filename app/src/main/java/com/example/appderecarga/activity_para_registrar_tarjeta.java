package com.example.appderecarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_para_registrar_tarjeta extends AppCompatActivity {

    EditText num_tarjeta;
    EditText fecha_tarjeta;
    EditText num_csv;
    Button cancelar;
    Button pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_registrar_tarjeta);

        cancelar = (Button) findViewById(R.id.btn_cancelar);
        pagar = (Button) findViewById(R.id.btnPagar);

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

        pagar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n_tarjeta = num_tarjeta.getText().toString();
                String f_tarjeta = fecha_tarjeta.getText().toString();
                String n_csv = num_csv.getText().toString();

                //codigo para el boton pagar
                String validacion = validarCampos(n_tarjeta, f_tarjeta, n_csv);

                if (validacion.equals("")){

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Tarjeta.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Tarjeta tarj = new Tarjeta(n_tarjeta,n_csv,f_tarjeta);
                            dbref.push().setValue(tarj);
                            ocultarTeclado();

                            Toast.makeText(activity_para_registrar_tarjeta.this, "La tarjeta fue registrada correctamente", Toast.LENGTH_SHORT).show();

                            Limpiar();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                }else {
                    //mensaje en caso falte algun campo
                    Toast.makeText(activity_para_registrar_tarjeta.this, "" + validacion, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /* ------------------------------- METODO PARA VALIDAR CAMPO -------------------------------- */
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

    /* ------------------------------- METODO PARA LIMPIAR CAMPO -------------------------------- */
    public void Limpiar(){
        num_tarjeta.setText("");
        num_csv.setText("");
        fecha_tarjeta.setText("");
    }

    /* ------------------------------ METODO PARA OCULTAR TECLADO ------------------------------- */
    public void ocultarTeclado(){
        View view = this.getCurrentFocus();

        if(view != null){
            InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}