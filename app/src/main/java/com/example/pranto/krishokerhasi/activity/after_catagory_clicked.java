package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.pranto.krishokerhasi.R;

public class after_catagory_clicked extends AppCompatActivity implements View.OnClickListener{

    private Button crops_button, fruits_button, fish_button, animal_button, home_button;

    private Animation animAlpha, animRotate, animScale, animTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_catagory_clicked);

        setButton();
    }

    int Getfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(i+1000);
        if(res.getCount()==0) {
            return 0;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
            buffer.append(res.getString(1));
        return Integer.parseInt(buffer.toString());
    }

    void addfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        int freq = Getfrequency(i)+1;
        boolean isUpdated = dbh.UpdateData(i+1000, String.valueOf(freq));
    }

    @Override
    public void onClick(final View view)
    {
        int id = view.getId();

        if(id==R.id.Crops_button)
        {
            addfrequency(0);
            Intent intent = new Intent(after_catagory_clicked.this, after_crop_clicked.class);
            animationStart(view, intent);
        }

        else if(id==R.id.Animal_button)
        {
            addfrequency(3);
            Intent intent = new Intent(after_catagory_clicked.this, after_animal_clicked.class);
            animationStart(view, intent);
        }

        else if(id==R.id.Fish_button)
        {
            addfrequency(2);
            Intent intent = new Intent(after_catagory_clicked.this, after_fish_clicked.class);
            animationStart(view, intent);
        }

        else if(id==R.id.Fruits_button)
        {
            addfrequency(1);
            Intent intent = new Intent(after_catagory_clicked.this, after_fruits_clicked.class);
            animationStart(view, intent);
        }

        else if(id==R.id.homebutton)
        {
            Intent intent = new Intent(after_catagory_clicked.this, MainActivity.class);
            animationStart(view, intent);
        }
    }

    public void setButton()
    {
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);

        //TextView freq = (TextView) findViewById(R.id.freq);
        crops_button = (Button)findViewById(R.id.Crops_button);
        fruits_button = (Button)findViewById(R.id.Fruits_button);
        fish_button = (Button)findViewById(R.id.Fish_button);
        animal_button = (Button)findViewById(R.id.Animal_button);
        home_button = (Button)findViewById(R.id.homebutton);

        crops_button.setOnClickListener(this);
        fruits_button.setOnClickListener(this);
        fish_button.setOnClickListener(this);
        animal_button.setOnClickListener(this);
        home_button.setOnClickListener(this);

    }

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
}

