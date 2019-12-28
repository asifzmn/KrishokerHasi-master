package com.example.pranto.krishokerhasi.java_class;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.pranto.krishokerhasi.R;
import com.example.pranto.krishokerhasi.activity.NotificationActivity;

/**
 * Created by pranto on 11/6/17.
 */

public class AlarmReceiver extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, NotificationActivity.class);
        String title = intent.getStringExtra("title");
        String text = intent.getStringExtra("text");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification =
                //builder.setContentTitle("Demo App Notification")
                builder.setContentTitle(title)
                        .setContentText(text)
                        .setTicker("New Message Alert!")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
