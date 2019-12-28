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

public class after_crop_clicked extends AppCompatActivity implements View.OnClickListener{

    private static Button[] buttonArray = new Button[10];
    private static TextView[] TextViewArray = new TextView[10];
    private int catagoryCode;
    private int itemCode;

    private Button homeButton;

    private Animation animAlpha, animRotate, animScale, animTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_crop_clicked);

        setButtonArray();
        setTextViewArray();

        catagoryCode = 0;

        int noOfItems = GetnoOfItems(0);

        for(int i=noOfItems; i<10; i++)
        {
            buttonArray[i].setVisibility(View.GONE);
            TextViewArray[i].setVisibility(View.GONE);
        }

        for (int i=0; i<noOfItems; i++) buttonArray[i].setOnClickListener(this);

        homeButton = (Button)findViewById(R.id.homebutton);
        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.rice_button)
        {
            itemCode = (catagoryCode*100)+0;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.wheat_button)
        {
            itemCode = (catagoryCode*100)+1;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.corn_button)
        {
            itemCode = (catagoryCode*100)+2;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.potato_button)
        {
            itemCode = (catagoryCode*100)+3;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.jute_button)
        {
            itemCode = (catagoryCode*100)+4;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.sugarcane_button)
        {
            itemCode = (catagoryCode*100)+5;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.brinjal_button)
        {
            itemCode = (catagoryCode*100)+6;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.potol_button)
        {
            itemCode = (catagoryCode*100)+7;
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id == R.id.homebutton)
        {
            Intent intent = new Intent(after_crop_clicked.this, MainActivity.class);
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
        buttonArray[0] = (Button)findViewById(R.id.rice_button);
        buttonArray[1] = (Button)findViewById(R.id.wheat_button);
        buttonArray[2] = (Button)findViewById(R.id.corn_button);
        buttonArray[3] = (Button)findViewById(R.id.potato_button);
        buttonArray[4] = (Button)findViewById(R.id.jute_button);
        buttonArray[5] = (Button)findViewById(R.id.sugarcane_button);
        buttonArray[6] = (Button)findViewById(R.id.brinjal_button);
        buttonArray[7] = (Button)findViewById(R.id.potol_button);
        buttonArray[8] = (Button)findViewById(R.id.onion_button);
        buttonArray[9] = (Button)findViewById(R.id.morich_button);

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);

    }

    /*-------------------------------------this function is used to set textArray-----------------------*/

    public void setTextViewArray()
    {
        TextViewArray[0] = (TextView) findViewById(R.id.rice_text);
        TextViewArray[1] = (TextView) findViewById(R.id.wheat_text);
        TextViewArray[2] = (TextView) findViewById(R.id.corn_text);
        TextViewArray[3] = (TextView) findViewById(R.id.potato_text);
        TextViewArray[4] = (TextView) findViewById(R.id.jute_text);
        TextViewArray[5] = (TextView) findViewById(R.id.sugarcane_text);
        TextViewArray[6] = (TextView) findViewById(R.id.brinjal_text);
        TextViewArray[7] = (TextView) findViewById(R.id.potol_text);
        TextViewArray[8] = (TextView) findViewById(R.id.onion_text);
        TextViewArray[9] = (TextView) findViewById(R.id.morich_text);
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
