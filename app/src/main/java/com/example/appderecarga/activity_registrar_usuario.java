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

public class activity_registrar_usuario extends AppCompatActivity {

    EditText et_nomb, et_ape, et_contra, et_usu;
    Button btnCance, btnRegi;

    String msj = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        et_nomb = (EditText) findViewById(R.id.txtNombre);
        et_ape = (EditText) findViewById(R.id.txtApellido);
        et_contra = (EditText) findViewById(R.id.txtContra);
        et_usu = (EditText) findViewById(R.id.txtUsuario);
        btnCance = (Button) findViewById(R.id.btnCancelar);
        btnRegi = (Button) findViewById(R.id.btnRegistrar);

        Registrar();
        Cancelar();
    }

    /* ------------------------------------ BOTON REGISTRAR ------------------------------------- */
    public void Registrar(){
        btnRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampo().isEmpty()) {
                    String nom = et_nomb.getText().toString().trim();
                    String ape = et_ape.getText().toString().trim();
                    String usu = et_usu.getText().toString().trim();
                    String con = et_contra.getText().toString().trim();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());
                    
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Usuario usuario = new Usuario(nom,ape,usu,con);
                            dbref.push().setValue(usuario);
                            ocultarTeclado();

                            Toast.makeText(activity_registrar_usuario.this,
                                    "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                            Limpiar();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(activity_registrar_usuario.this,
                                    "Se produjo un error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(activity_registrar_usuario.this, "Completar el campo "
                            + msj, Toast.LENGTH_LONG).show();
                }

            } // Cierra el OnClick
        });
    }

    /* ------------------------------------- BOTON CANCELAR ------------------------------------- */
    public void Cancelar(){
        btnCance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ant = new Intent(activity_registrar_usuario.this, MainActivity.class);
                startActivity(ant);
            }
        });
    }

    /* ------------------------------- METODO PARA VALIDAR CAMPO -------------------------------- */
    public String validarCampo(){

        if (et_nomb.getText().toString().trim().isEmpty()){
            msj = "nombre";
        } else if ((et_ape.getText().toString().trim().isEmpty())) {
            msj = "apellido";
        } else if ((et_usu.getText().toString().trim().isEmpty())) {
            msj = "usuario";
        } else if ((et_contra.getText().toString().trim().isEmpty())) {
            msj = "contrasenia";
        } else {
            msj = "";
        }

        return msj;
    }

    /* ------------------------------- METODO PARA LIMPIAR CAMPO -------------------------------- */
    public void Limpiar(){
        et_nomb.setText("");
        et_ape.setText("");
        et_usu.setText("");
        et_contra.setText("");
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