package com.example.gabriel.thediarista;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class Notificacao extends AppCompatActivity implements View.OnClickListener{



    boolean Notific = false;
    int noti = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button btnNotif = (Button) findViewById(R.id.btnNotification);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notiBuilder = new NotificationCompat
                        .Builder(Notificacao.this)
                        .setTicker("Termine o Cadastro!")
                        .setContentTitle("Cadastro Quase terminado.")
                        .setContentText("Vá para perfil para terminar seu cadastro...")
                        .setSmallIcon(R.drawable.icone_notification);

                Intent infoIntent = new Intent(Notificacao.this, Perfil.class);

                TaskStackBuilder tStack = TaskStackBuilder.create(Notificacao.this);

                tStack.addParentStack(Menu.class);
                tStack.addNextIntent(infoIntent);
                PendingIntent pendingIntent = tStack.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                notiBuilder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(noti, notiBuilder.build());

                Notific = true;
            }


        });

//        btnRegistro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                NotificationCompat.Builder notiBuilder = new NotificationCompat
//                        .Builder(MainActivity.this)
//                        .setSmallIcon(R.drawable.icone_notification)
//                        .setTicker("Bem Vindo")
//                        .setContentTitle("Cadastro Completado.")
//                        .setContentText("Obrigado por se Cadastrar");
//
//
//                Intent infoIntent = new Intent(MainActivity.this, Menu.class);
//
//                TaskStackBuilder tStack = TaskStackBuilder.create(MainActivity.this);
//
//                tStack.addParentStack(Menu.class);
//                tStack.addNextIntent(infoIntent);
//                PendingIntent pendingIntent = tStack.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
//
//                notiBuilder.setContentIntent(pendingIntent);
//
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(noti, notiBuilder.build());
//
//                Notific = true;
//            }
//
//
//        });




    }

    @Override
    public void onClick(View v) {

    }
}
