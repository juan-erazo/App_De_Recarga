package com.example.appderecarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText et_usua, et_clave;
    Button btn_Regis, btn_Ingr;
    String msj = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_usua = (EditText) findViewById(R.id.txtUsu);
        et_clave = (EditText) findViewById(R.id.txtClave);
        btn_Regis = (Button) findViewById(R.id.btnRegis);
        btn_Ingr = (Button) findViewById(R.id.btnIngresar);

        Registra();
        Ingresar();
    }

    /* ------------------------------------ BOTON REGISTRAR ------------------------------------- */
    private void Registra(){
        btn_Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = new Intent(MainActivity.this, activity_registrar_usuario.class);
                startActivity(sig);
            }
        });
    }

    /* ------------------------------------- BOTON INGRESAR ------------------------------------- */
    private void Ingresar(){
        btn_Ingr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarCampo().isEmpty()) {
                    String usuario = et_usua.getText().toString().trim();
                    String clave = et_clave.getText().toString().trim();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot x : snapshot.getChildren()) {

                                if (x.child("usuario").getValue().toString().equalsIgnoreCase(usuario) &&
                                        x.child("contrasenia").getValue().toString().equals(clave)) {

                                    Intent sig = new Intent(MainActivity.this, activity_operacion_recarga.class);
                                    startActivity(sig);
                                    Toast.makeText(MainActivity.this, "Ha ingresado correctamente", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }

                            et_usua.setText("");
                            et_clave.setText("");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "Se generó un error al ingresar", Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, "Falta completar el campo " + msj, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /* ------------------------------- METODO PARA VALIDAR CAMPO -------------------------------- */
    private String validarCampo(){

        if (et_usua.getText().toString().trim().isEmpty()){
            msj = "usuario";
        } else if ((et_clave.getText().toString().trim().isEmpty())) {
            msj = "clave";
        } else {
            msj = "";
        }

        return msj;
    }
}