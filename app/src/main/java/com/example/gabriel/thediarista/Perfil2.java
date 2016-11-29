package com.example.gabriel.thediarista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Perfil2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil2);
    }



    public void VoltaPerfil(View view) {
        startActivity(new Intent(this, Perfil.class));

    }
}
