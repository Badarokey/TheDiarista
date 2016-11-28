package com.example.gabriel.thediarista;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    private Button btnRegistro;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnLogar;
    public EditText txtNome;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseauth;

    boolean Notific = false;
    int noti = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseauth = firebaseauth.getInstance();

        if(firebaseauth.getCurrentUser() != null) {
            //activity_menu atividade
            finish();
            startActivity(new Intent(getApplicationContext(), Menu.class));

        }

        progressDialog = new ProgressDialog(this);

        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        txtNome = (EditText) findViewById(R.id.txtNome);

        btnRegistro.setOnClickListener(this);
        btnLogar.setOnClickListener(this);


    }

    private void registerUser(){

        String nome = txtNome.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();


        if(TextUtils.isEmpty(nome)){
            //campo nome esta vazio
            Toast.makeText(this, "Por Favor insira um Nome", Toast.LENGTH_SHORT).show();
            //parar a funçao da execuçao
            return;
        }

        if(TextUtils.isEmpty(email)){
            //campo email esta vazio
            Toast.makeText(this, "Por Favor insira um E-mail", Toast.LENGTH_SHORT).show();
            //parar a funçao da execuçao
            return;
        }

        if(TextUtils.isEmpty(senha)){
            //campo senha esta vazio
            Toast.makeText(this, "Por Favor insira uma Senha", Toast.LENGTH_SHORT).show();
            //parar execuçao
            return;
        }



        //se tiver correto mostrando barra de progresso

        progressDialog.setMessage("registrando Usuario...");
        progressDialog.show();

        firebaseauth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //registrado e logado. iniciando atividade de activity_perfil
                                finish();
                                startActivity(new Intent(getApplicationContext(), Menu.class));

                        }else{
                            Toast.makeText(MainActivity.this, "Nao conseguiu registrar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == btnRegistro){
            registerUser();

//
        }

        if (view == btnLogar){
            //abre atividade de login
            startActivity(new Intent(new Intent(this, Login.class)));
        }
    }
}
