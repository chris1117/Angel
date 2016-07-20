package com.example.user.androidswitch;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by User on 7/9/2016.
 */
public class InfoActivity extends Activity {

    final int val = 0;
    int passAge;
    String name, mail, age;
    EditText tv1;
    TextView tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_info_main);

        tv1 = (EditText) findViewById(R.id.editText3);
        tv2 = (TextView) findViewById(R.id.textView8);
        tv3 = (TextView) findViewById(R.id.textView10);

        Intent intent1 = getIntent();
        name = intent1.getStringExtra("userName");
        mail = intent1.getStringExtra("userMail");
        age = Integer.toString(intent1.getIntExtra("userAge", 10));

        passAge = intent1.getIntExtra("userAge", 5);

        tv1.setText(name);
        tv2.setText(mail);
        tv3.setText(age);

        if (passAge >= val){

            //int notifyID = 1, numMessages = 0;

            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle(getString(R.string.show_Title))
                    .setContentText(getString(R.string.show_text))
                    .setVibrate(new long[] {500, 500, 500, 500})
                    .setLights(Color.BLUE, 500, 500).setSound(sound)
                    .setSmallIcon(R.drawable.icon_notification)
                    .setAutoCancel(true);

            Intent intent = new Intent(this, NoticationActivity.class);
            intent.putExtra(getString(R.string.send_Age), passAge);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

            stackBuilder.addParentStack(NoticationActivity.class)
                        .addNextIntent(intent);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //builder.setNumber(++numMessages);

            notificationManager.notify(0, builder.build());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

   /* public void showNotification(View view) {

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bundle extras = getIntent().getExtras();
        passAge = extras.getInt("userAge");

        int notifyId = 1;
        long[] vibDis ={1000, 1000, 1000, 1000};

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.drawable.icon_notification)
                .setContentTitle("New Post from " + name)
                .setContentText("Age Group Identified")
                .setLights(Color.BLUE, 500, 500)
                .setVibrate(vibDis).setSound(sound)
                .setAutoCancel(true);

        Intent intent2 = new Intent(this, NoticationActivity.class);
        intent2.putExtra("sendAge", passAge);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(NoticationActivity.class)
                    .addNextIntent(intent2);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifyId, builder.build());

    }
    */

}
