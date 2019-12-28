package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.pranto.krishokerhasi.R;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.MainActivity_socialnetwork;

public class common_page extends AppCompatActivity implements View.OnClickListener{

    private static Button[] buttonArray = new Button[7];
    private Button button;
    private Animation animAlpha, animRotate, animScale, animTranslate;
    private int itemCode, audioCode;
    private String ItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_page);

        setButtonArray();

        itemCode = getIntent().getIntExtra("itemcode", 0);
        audioCode = (itemCode/10)+(itemCode%10);
        ItemName = GetItemName(audioCode/10,audioCode%10);

        for (int i=0; i<7; i++) buttonArray[i].setOnClickListener(this);
    }

    private String GetItemName(int catagoryCode, int indexCode) {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(catagoryCode+600);
        if(res.getCount()==0)
            return "";

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {buffer.append(res.getString(1));}

        String Catagory=buffer.toString();
        //return Catagory;

        String[] Items = Catagory.split(",");
        return Items[indexCode];
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.text_button)
        {
            GetOfflineData(itemCode);
        }

        else if(id==R.id.audio_button)
        {
            Intent intent = new Intent(common_page.this, audio_file.class);
            intent.putExtra("audioCode", audioCode);
            animationStart(view, intent);
        }
        else if(id==R.id.ask_button)
        {
            Intent intent = new Intent(common_page.this, text_search.class);
            animationStart(view, intent);
        }

        else if(id == R.id.hotline_button)
        {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:+16123"));
            animationStart(view, callIntent);
        }

        else if(id == R.id.share_button)
        {
            int from_where = 1;
            Intent intent = new Intent(common_page.this, MainActivity_socialnetwork.class);
            intent.putExtra("from_where", from_where);
            animationStart(view, intent);
        }

        else if(id == R.id.notification_button)
        {
            Intent intent = new Intent(common_page.this, notification.class);
            intent.putExtra("ItemName", ItemName);
            animationStart(view, intent);
        }

        else if(id==R.id.homebutton)
        {
            Intent intent = new Intent(common_page.this, MainActivity.class);
            animationStart(view, intent);
        }
    }

    public void setButtonArray()
    {
        buttonArray[0] = (Button)findViewById(R.id.text_button); //text_button
        buttonArray[1] = (Button)findViewById(R.id.audio_button); //audio_button
        buttonArray[2] = (Button)findViewById(R.id.ask_button); //ask_button
        buttonArray[3] = (Button)findViewById(R.id.hotline_button); //hotline_button
        buttonArray[4] = (Button)findViewById(R.id.notification_button); //notification_button
        buttonArray[5] = (Button)findViewById(R.id.share_button); //share_button
        buttonArray[6] = (Button)findViewById(R.id.homebutton); //share_button

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);
    }

    /*--------------------------------animationStart function is used for animation---------------------------------*/

    public void animationStart(View view, final Intent intent)
    {
        view.startAnimation(animScale);

        animScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    void GetOfflineData(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        boolean empty=false;

        Cursor res = dbh.getAllData(i);
        if(res.getCount()==0) {
            empty = true;
            //showMessage("Error","Nothing");
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {buffer.append(res.getString(1)+"\n\n");}

        String getrec=buffer.toString();

        if(empty)
            getrec = "Empty";

        CreateIntent(getrec);
    }

    void CreateIntent(String data)
    {
        Intent i = new Intent(common_page.this, InfoPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("stuff", data);
        i.putExtras(bundle);
        startActivity(i);
    }
    void ToastMethod(String str)
    {Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();}

}
