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

public class after_animal_clicked extends AppCompatActivity implements View.OnClickListener{

    private static Button[] buttonArray = new Button[10];
    private static TextView[] TextViewArray = new TextView[10];
    private Animation animAlpha, animRotate, animScale, animTranslate;
    private Button homeButton;

    private int itemCode, catagoryCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_animal_clicked);

        setButtonArray();
        setTextViewArray();

        int noOfItems = GetnoOfItems(3);

        catagoryCode = 3;

        for(int i=noOfItems; i<8; i++)
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

        if(id==R.id.cow_button)
        {
            itemCode = (catagoryCode*100)+0;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.buffalo_button)
        {
            itemCode = (catagoryCode*100)+1;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.goat_button)
        {
            itemCode = (catagoryCode*100)+2;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.sheep_button)
        {
            itemCode = (catagoryCode*100)+3;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.chicken_button)
        {
            itemCode = (catagoryCode*100)+4;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.duck_button)
        {
            itemCode = (catagoryCode*100)+5;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.pegeon_button)
        {
            itemCode = (catagoryCode*100)+6;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }

        else if(id==R.id.koel_button)
        {
            itemCode = (catagoryCode*100)+7;
            Intent intent = new Intent(after_animal_clicked.this, common_page.class);
            intent.putExtra("itemcode", itemCode);
            animationStart(view, intent);
        }


        else if(id == R.id.homebutton)
        {
            Intent intent = new Intent(after_animal_clicked.this, MainActivity.class);
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
        buttonArray[0] = (Button)findViewById(R.id.cow_button);
        buttonArray[1] = (Button)findViewById(R.id.buffalo_button);
        buttonArray[2] = (Button)findViewById(R.id.goat_button);
        buttonArray[3] = (Button)findViewById(R.id.sheep_button);
        buttonArray[4] = (Button)findViewById(R.id.chicken_button);
        buttonArray[5] = (Button)findViewById(R.id.duck_button);
        buttonArray[6] = (Button)findViewById(R.id.pegeon_button);
        buttonArray[7] = (Button)findViewById(R.id.koel_button);

        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);
    }

    /*-------------------------------------this function is used to set textArray-----------------------*/

    public void setTextViewArray()
    {
        TextViewArray[0] = (TextView) findViewById(R.id.cow_text);
        TextViewArray[1] = (TextView) findViewById(R.id.bufelo_text);
        TextViewArray[2] = (TextView) findViewById(R.id.goat_text);
        TextViewArray[3] = (TextView) findViewById(R.id.sheep_text);
        TextViewArray[4] = (TextView) findViewById(R.id.chicken_text);
        TextViewArray[5] = (TextView) findViewById(R.id.duck_text);
        TextViewArray[6] = (TextView) findViewById(R.id.peageon_text);
        TextViewArray[7] = (TextView) findViewById(R.id.koel_text);
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
