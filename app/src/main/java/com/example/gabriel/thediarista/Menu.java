package com.example.gabriel.thediarista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView txtperfil;
    private Button btnlogout;

    FloatingActionButton btn_set;
    FloatingActionButton set_perfil;
    Animation set_open;
    Animation set_close;
    boolean isOpen = false;

    public Button tiraFoto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firebaseAuth = firebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));

        }


        final Intent intentPerfil = new Intent(this, Perfil.class);


        tiraFoto = (Button)findViewById(R.id.btnBaixoE);
        btn_set = (FloatingActionButton)findViewById(R.id.btn_set);
        set_perfil = (FloatingActionButton)findViewById(R.id.btn_perfil);

        set_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.set_open);
        set_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.set_close);

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpen) {
                    set_perfil.startAnimation(set_close);
                    set_perfil.setClickable(false);
                    isOpen = false;
                }
                else
            {
                set_perfil.startAnimation(set_open);
                set_perfil.setClickable(true);
                isOpen = true;

            }
            }


        });



        set_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPerfil);


            }
        });






        FirebaseUser usuario = firebaseAuth.getCurrentUser();

        txtperfil = (TextView) findViewById(R.id.txtperfil);

        txtperfil.setText("Bem vindo " + usuario.getEmail());

        btnlogout = (Button) findViewById(R.id.btnlogout);

        btnlogout.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {

        if (view == btnlogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }


    }

    public void mapa(View view) {
        startActivity(new Intent(this, Mapas.class));

    }

    public void tiraFoto(View view) {
        startActivity(new Intent(this, Foto.class));

    }
}
