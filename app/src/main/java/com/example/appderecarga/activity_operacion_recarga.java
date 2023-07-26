package com.example.appderecarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_operacion_recarga extends AppCompatActivity {

    EditText monto, numero;
    Spinner spinner1;

    String num,ope,mon;
    private Button btn_Atr, btn_Recar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacion_recarga);

        monto = (EditText) findViewById(R.id.txtSaldo);
        numero = (EditText) findViewById(R.id.txtNumero);
        btn_Atr = (Button) findViewById(R.id.btnAtr);
        btn_Recar = (Button) findViewById(R.id.btnRecargar);
        spinner1 = (Spinner) findViewById(R.id.Spiner_empresa);

        String[] empresas = {"Claro", "Movistar", "Entel", "Bitel"};

        ArrayAdapter <String> empresa_seleccion = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, empresas);
        spinner1.setAdapter(empresa_seleccion);

        btn_Recar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empresa = spinner1.getSelectedItem().toString();

                if(empresa.equals("Claro")){
                    ope = "Claro";
                } else if (empresa.equals("Movistar")){
                    ope = "Movistar";
                } else if (empresa.equals("Entel")){
                    ope = "Entel";
                } else if (empresa.equals("Bitel")){
                    ope = "Bitel";
                }

                num = numero.getText().toString();
                mon = monto.getText().toString();

                //codigo para el boton pagar
                String validacion = validarCamp();

                if (validacion.equals("")){

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Recarga.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Recarga rec = new Recarga(num,ope,mon);
                            dbref.push().setValue(rec);

                            Toast.makeText(activity_operacion_recarga.this, "La recarga fue registrada correctamente", Toast.LENGTH_SHORT).show();

                            Limpia();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                }else {
                    //mensaje en caso falte algun campo
                    Toast.makeText(activity_operacion_recarga.this, "" + validacion, Toast.LENGTH_SHORT).show();
                }
            }
        });


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

    /* ------------------------------- METODO PARA VALIDAR CAMPO -------------------------------- */
    public String validarCamp() {

        if (numero.equals("")){
            return "Ingrese su numero de celular";
        } else if(monto.equals("")) {
            return "Ingrese el monto a recargar";
        } else if(spinner1.getSelectedItemId() == -1) {
            return "Seleccione una operadora";
        } else {
            return "";
        }
    }
    /* ------------------------------- METODO PARA LIMPIAR CAMPO -------------------------------- */
    public void Limpia(){
        numero.setText("");
        monto.setText("");
        spinner1.setSelection(-1);
    }

}