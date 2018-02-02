package com.example.david.quicktrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.david.quicktrade.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {
    Button botonAgregarUsuario, botonAgregarProducto, botonTodosProductos, botonTodosUsuarios, botonSeguidores;
    EditText seguidoresText;
    DatabaseReference bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_usuario));

        botonAgregarUsuario = (Button)findViewById(R.id.boton_agrUsu);
        botonAgregarProducto = (Button)findViewById(R.id.boton_agrPro);
        botonTodosProductos = (Button)findViewById(R.id.botonTodosProductos);
        botonTodosUsuarios = (Button)findViewById(R.id.botonTodosUsuarios);
        botonSeguidores = (Button)findViewById(R.id.botonSeguidores);
        seguidoresText = (EditText)findViewById(R.id.seguidoresText);

        seguidoresText.setVisibility(View.INVISIBLE);


        botonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Agregar_usuario.class);
                startActivity(intent);
            }
        });

        botonAgregarProducto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Agregar_producto.class);
                startActivity(intent);
            }
        });

        botonTodosProductos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Todos_productos.class);
                startActivity(intent);
            }
        });

        botonTodosUsuarios.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Todos_usuarios.class);
                startActivity(intent);
            }
        });

        botonSeguidores.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {

            seguidoresText.setVisibility(View.VISIBLE);
        }
        });

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bindInfoProducto(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //Aquí se rellena la información del usuario
    private void bindInfoProducto(DataSnapshot dataSnapshot) {
        Usuario u = dataSnapshot.getValue(Usuario.class);
         seguidoresText.setText(u.getSeguidores());

    }
}
