package com.example.david.quicktrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.david.quicktrade.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Todos_usuarios extends AppCompatActivity {

    DatabaseReference bbdd ,bbdd2;
    private ListView listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_usuarios);

        listaUsuarios = (ListView) findViewById(R.id.listaUsuarios);

        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_usuario));

        bbdd2 = (FirebaseDatabase.getInstance().getReference("usuario"));

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = datasnapshot.getValue(Usuario.class);

                    String nombreusuario = usuario.getNombreusuario();
                    listado.add(nombreusuario);

                }

                adaptador = new ArrayAdapter<String>(Todos_usuarios.this, android.R.layout.simple_list_item_1, listado);
                listaUsuarios.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listaUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bbdd2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        bbdd2.child("seguidores").setValue(bbdd.getKey());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
