package com.example.gabriel.thediarista;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Gabriel on 14/11/2016.
 */

public class Perfil extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseauth;

    public Button btnVoltarMenu;
    private Button btnSalvar;

    public EditText editNome;
    public EditText editEmail;
    public EditText editTel;
    public EditText editData;

    public RadioGroup rg;
    public RadioButton radiob;
    private RadioButton masc;
    private RadioButton fem;

    private ProgressDialog progressDialog;


    //    public NotificationManager notificationManager;
    boolean Notific = false;
    int noti = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        btnVoltarMenu = (Button) findViewById(R.id.btnVoltarMenu);
        btnSalvar = (Button) findViewById(R.id.btnSalvar_perf);


        final Intent menuVoltar = new Intent(this, Menu.class);

        btnVoltarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(menuVoltar);
            }
        });

        masc = (RadioButton) findViewById(R.id.raioMasc);
        fem = (RadioButton) findViewById(R.id.raioFem);

        rg = (RadioGroup) findViewById(R.id.radioG);

        masc.setOnClickListener(Perfil.this);
        fem.setOnClickListener(Perfil.this);


    }


    public void RadioBotao(View view) {

        int radioBtn = rg.getCheckedRadioButtonId();

        radiob = (RadioButton) findViewById(radioBtn);
//        switch (view.getId()){
//            case R.id.raioMasc:
//
//                break;
//
//            case R.id.raioFem:
//
//                break;
//        }
    }

    private void updateUser() {

        String nome = editNome.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String telefone = editTel.getText().toString().trim();


        if (TextUtils.isEmpty(nome)) {
            //campo nome esta vazio
            Toast.makeText(this, "Por Favor insira um Nome", Toast.LENGTH_SHORT).show();
            //parar a funçao da execuçao
            return;
        }

        if (TextUtils.isEmpty(email)) {
            //campo email esta vazio
            Toast.makeText(this, "Por Favor Atualize o E-mail", Toast.LENGTH_SHORT).show();
            //parar a funçao da execuçao
            return;
        }

        if (TextUtils.isEmpty(telefone)) {
            //campo senha esta vazio
            Toast.makeText(this, "Por Favor insira um telefone", Toast.LENGTH_SHORT).show();
            //parar execuçao
            return;
        }

        progressDialog.setMessage("Atualizando Usuario...");
        progressDialog.show();


        //se tiver correto mostrando barra de progresso


    }


    @Override
    public void onClick(View v) {

    }

    //adicionar botao salvar ao perfil
    
//    public void Salvar(View view) {
//        if (view == btnSalvar) {
//            updateUser();
//
//        }
//    }
    public void Perfil_2(View view) {
        startActivity(new Intent(this, Perfil2.class));

    }


}
