package com.example.appderecarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_registrar_usuario extends AppCompatActivity {

    EditText et_nomb, et_ape, et_contra;
    TextView tv_usu;
    Button btnCance, btnRegi;

    String msj = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        et_nomb = findViewById(R.id.txtNombre);
        et_ape = findViewById(R.id.txtApellido);
        et_contra = findViewById(R.id.txtContra);
        tv_usu = findViewById(R.id.txtUsuario);
        btnCance = findViewById(R.id.btnCancelar);
        btnRegi = findViewById(R.id.btnRegistrar);

        Registrar();
    }

    public void Registrar(){
        btnRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampo().isEmpty()) {
                    String nom = et_nomb.getText().toString().trim();
                    String ape = et_ape.getText().toString().trim();
                    String con = et_contra.getText().toString().trim();
                    String usu = nom + "." + ape;

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
                }

            } // Cierra el OnClick
        });
    } // Cierra el boton registrar

    public String validarCampo(){

        if (et_nomb.getText().toString().trim().isEmpty()){
            msj = "Nombre";
            Toast.makeText(this, "Completar el campo " + msj, Toast.LENGTH_LONG).show();
        } else if ((et_ape.getText().toString().trim().isEmpty())) {
            msj = "Apellido";
            Toast.makeText(this, "Completar el campo " + msj, Toast.LENGTH_LONG).show();
        } else if ((et_contra.getText().toString().trim().isEmpty())) {
            msj = "Contraseña";
            Toast.makeText(this, "Completar el campo " + msj, Toast.LENGTH_LONG).show();
        } else {
            msj = "";
        }

        return msj;
    } // Cierra el metodo validar

    public void Limpiar(){
        et_nomb.setText("");
        et_ape.setText("");
        tv_usu.setText("");
        et_contra.setText("");
    }


    public void ocultarTeclado(){
        View view = this.getCurrentFocus();

        if(view != null){
            InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el metodo ocultar teclado
}