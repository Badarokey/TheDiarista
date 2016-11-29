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

public class Login extends AppCompatActivity implements View.OnClickListener{


    private Button btnLogar;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnRegistro;

    private ProgressDialog progressDialog;
    private ProgressDialog progressDialog2;

    private FirebaseAuth firebaseauth;

    boolean Notific = false;
    int noti = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseauth = firebaseauth.getInstance();

        if(firebaseauth.getCurrentUser() != null){
            //activity_menu atividade
            finish();
            startActivity(new Intent(getApplicationContext(), Menu.class));

        }
        //botoes e textview
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        //barra de progresso
        progressDialog =new ProgressDialog(this);
        progressDialog2  = new ProgressDialog(this);

        //botoes logar cadastro
        btnLogar.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);



    }

    private void UserLogin(){
        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();

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

        progressDialog.setMessage("Logando ...");
        progressDialog.show();

        firebaseauth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            // iniciar activity_menu
                            finish();
                            startActivity(new Intent(getApplicationContext(), Menu.class));


                        }else{
                            progressDialog2.setMessage("E-mail ou Senha incorretos");
                            progressDialog2.show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogar){


            UserLogin();

            NotificationCompat.Builder notiBuilder = new NotificationCompat
                    .Builder(Login.this)
                    .setSmallIcon(R.drawable.icone_notification)
                    .setTicker("Bem Vindo")
                    .setContentTitle("Cadastro Quase Completo.")
                    .setContentText("Obrigado por se Cadastrar");


            Intent infoIntent = new Intent(Login.this, Menu.class);

            TaskStackBuilder tStack = TaskStackBuilder.create(Login.this);

            tStack.addParentStack(Menu.class);
            tStack.addNextIntent(infoIntent);
            PendingIntent pendingIntent = tStack.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

            notiBuilder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(noti, notiBuilder.build());

            Notific = true;

        }

        if (view == btnRegistro){
            startActivity(new Intent(this, MainActivity.class));
            //abre atividade de login
        }
    }
}
