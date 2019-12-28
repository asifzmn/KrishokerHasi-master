package com.example.pranto.krishokerhasi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.example.pranto.krishokerhasi.R;

public class InfoPage extends AppCompatActivity {

    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        Bundle bundle = getIntent().getExtras();
        String text= bundle.getString("stuff");

        TextView data = (TextView)findViewById(R.id.textView);
        Button button = (Button)findViewById(R.id.button);


        String[] separated = text.split("!");
        text = "";

        for (int i = 0; i < separated.length; i++) {
            System.out.println(separated[i]);
            System.out.println("Split");

            if(separated[i].length() !=0 && separated[i].charAt(0) == '*') {
                text+="<font color=red>" + separated[i].substring(1,separated[i].length()) + "</font><br>";
                //data.append(separated[i].substring(1,separated[i].length())+"\n");
                //data.setTextColor(Color.parseColor("#FF0000"));
                //data.append(separated[i]+"*\n");
            }
            else
                text+=separated[i]+"<br>";
        }
        data.setText(Html.fromHtml(text));
        //data.setText(text);
    }
}
