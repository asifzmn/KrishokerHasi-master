package com.example.pranto.krishokerhasi.activity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.pranto.krishokerhasi.R;

public class notification extends AppCompatActivity {

    private Button sendNotificationButton;

    private EditText editMsg, editTime;

    String ItemCode;

    int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        sendNotificationButton = (Button)findViewById(R.id.send_notification_button);

        editMsg = (EditText)findViewById(R.id.messageText);
        editTime = (EditText)findViewById(R.id.timeText);
        ItemCode = getIntent().getExtras().getString("ItemName");

        sendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Time = editTime.getText().toString();
                String Msg = editMsg.getText().toString();
                if(!(isEmpty(editTime)||isEmpty(editMsg)))
                    AlarmNotification(Integer.parseInt(Time),Msg);
            }
        });
    }

    private void AlarmNotification(int time, String msg) {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar cal =Calendar.getInstance();
        cal.add(Calendar.SECOND, time);

        Intent intent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        intent.putExtra("title","Krishoker Hasi "+ItemCode);
        intent.putExtra("text",msg+String.valueOf(time));
        PendingIntent broadcast = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        am.setExact(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),broadcast);

    }

    void ToastMethod(String str)
    {Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();}

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
