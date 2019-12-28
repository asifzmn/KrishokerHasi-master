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

public class after_fish_clicked extends AppCompatActivity implements View.OnClickListener{

    private static Button[] buttonArray = new Button[10];
    private static TextView[] TextViewArray = new TextView[10];
    private Animation animAlpha, animRotate, animScale, animTranslate;
    private Button homeButton;

    private int itemCode, catagoryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_fish_clicked);

        setButtonArray();
        setTextViewArray();

        int noOfItems = GetnoOfItems(2);

        for(int i=noOfItems; i<10; i++)
        {
            buttonArray[i].setVisibility(View.GONE);
            TextViewArray[i].setVisibility(View.GONE);
        }

        catagoryCode = 2;

        for (int i=0; i<noOfItems; i++) buttonArray[i].setOnClickListener(this);

        homeButton = (Button)findViewById(R.id.homebutton);
        homeButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.rui_button)
        {
            itemCode = (catagoryCode*100)+0;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.katla_button)
        {
            itemCode = (catagoryCode*100)+1;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.mrigel_button)
        {
            itemCode = (catagoryCode*100)+2;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.chingri_button)
        {
            itemCode = (catagoryCode*100)+3;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.koi_button)
        {
            itemCode = (catagoryCode*100)+4;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.magur_button)
        {
            itemCode = (catagoryCode*100)+5;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.kalbaush_button)
        {
            itemCode = (catagoryCode*100)+6;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.telapia_button)
        {
            itemCode = (catagoryCode*100)+7;
            Intent intent = new Intent(after_fish_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }


        else if(id == R.id.homebutton)
        {
            Intent intent = new Intent(after_fish_clicked.this, MainActivity.class);
            animationStart(view, intent);
        }
    }


    /*------------------this function will return no. of items for crops from sqlite database-------------*/

    public int GetnoOfItems(int id)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Cursor res = databaseHelper.getAllData(id+500);
        if(res.getCount()==0)
            return 0;

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext())
            buffer.append(res.getString(1));

        return Integer.parseInt(buffer.toString());
    }

    /*-------------------------------------this function is used to set buttonArray-----------------------*/

    public void setButtonArray()
    {
        buttonArray[0] = (Button)findViewById(R.id.rui_button);
        buttonArray[1] = (Button)findViewById(R.id.katla_button);
        buttonArray[2] = (Button)findViewById(R.id.mrigel_button);
        buttonArray[3] = (Button)findViewById(R.id.chingri_button);
        buttonArray[4] = (Button)findViewById(R.id.koi_button);
        buttonArray[5] = (Button)findViewById(R.id.magur_button);
        buttonArray[6] = (Button)findViewById(R.id.kalbaush_button);
        buttonArray[7] = (Button)findViewById(R.id.telapia_button);
        buttonArray[8] = (Button)findViewById(R.id.silver_carp_button);
        buttonArray[9] = (Button)findViewById(R.id.grass_carp_button);

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);
    }

    /*-------------------------------------this function is used to set textArray-----------------------*/

    public void setTextViewArray()
    {
        TextViewArray[0] = (TextView) findViewById(R.id.rui_text);
        TextViewArray[1] = (TextView) findViewById(R.id.katla_text);
        TextViewArray[2] = (TextView) findViewById(R.id.mrigel_text);
        TextViewArray[3] = (TextView) findViewById(R.id.chingri_text);
        TextViewArray[4] = (TextView) findViewById(R.id.koi_text);
        TextViewArray[5] = (TextView) findViewById(R.id.magur_text);
        TextViewArray[6] = (TextView) findViewById(R.id.kalbaush_text);
        TextViewArray[7] = (TextView) findViewById(R.id.telapia_text);
        TextViewArray[8] = (TextView) findViewById(R.id.silver_carp_text);
        TextViewArray[9] = (TextView) findViewById(R.id.grass_carp_text);
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
