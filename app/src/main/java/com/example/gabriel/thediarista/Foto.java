package com.example.gabriel.thediarista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Foto extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);




    }

    public void tirarFoto(View view) {
        Intent intentFoto = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intentFoto, 0);
    }

    @Override
    protected void onActivityResult(int requestedCode, int resultCode, Intent data){
        if(data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                Bitmap imagem = (Bitmap) bundle.get("data");

                ImageView iv = (ImageView) findViewById(R.id.imagemView);
                iv.setImageBitmap(imagem);
            }
        }
    }

    public void Voltar(View view) {
        startActivity(new Intent(this, Menu.class));

    }
}
