package com.example.pranto.krishokerhasi.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pranto.krishokerhasi.R;

public class text_search extends AppCompatActivity implements View.OnClickListener{

    private Button search_button;
    private EditText input_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_search);

        search_button = (Button)findViewById(R.id.searching_button);
        search_button.setOnClickListener(this);

        input_text = (EditText)findViewById(R.id.editTextInput);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.searching_button)
        {
            try{

                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                String term = input_text.getText().toString();
                intent.putExtra(SearchManager.QUERY,term);
                startActivity(intent);
            }catch (Exception e){}
        }
    }
}
