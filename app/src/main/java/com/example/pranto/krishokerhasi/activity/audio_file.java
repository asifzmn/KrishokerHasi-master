package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pranto.krishokerhasi.R;

public class audio_file extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer mp;
    public static int pressCount = 0, flag;
    Button play_button;
    private int audioCode;

    boolean isBackbuttonPressed = false;

    private MediaPlayer mediaPlayer[] = new MediaPlayer[40];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_file);

        flag = getIntent().getIntExtra("flag", 0);
        audioCode = getIntent().getIntExtra("audioCode", 0);

        play_button = (Button)findViewById(R.id.start_button);

        play_button.setOnClickListener(this);

        setMusic();

        //mp = MediaPlayer.create(audio_file.this, R.raw.maine);

        //onBackPressed();
    }


    @Override
    public void onBackPressed() {
        pressCount = 0;
        mediaPlayer[audioCode].stop();
        Intent intent = new Intent(audio_file.this, common_page.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id == R.id.start_button)
        {
            if(pressCount == 0)
            {
                pressCount = 1;
                play_button.setBackgroundResource(R.mipmap.music_pause1);
                mediaPlayer[audioCode].start();
            }
            else if(pressCount == 1)
            {
                pressCount = 0;
                play_button.setBackgroundResource(R.mipmap.music_palyer1);
                mediaPlayer[audioCode].pause();
            }
        }
    }

    /*-----------------------setMusic() function is used to set the music array------------------------*/

    public void setMusic()
    {
        /*------------------------set the crops items audio file----------------------------------------*/

        mediaPlayer[0] = MediaPlayer.create(audio_file.this, R.raw.rice);
        mediaPlayer[1] = MediaPlayer.create(audio_file.this, R.raw.wheat);
        mediaPlayer[2] = MediaPlayer.create(audio_file.this, R.raw.corn);
        mediaPlayer[3] = MediaPlayer.create(audio_file.this, R.raw.potato);
        mediaPlayer[4] = MediaPlayer.create(audio_file.this, R.raw.jute);
        mediaPlayer[5] = MediaPlayer.create(audio_file.this, R.raw.sugarcane);
        mediaPlayer[6] = MediaPlayer.create(audio_file.this, R.raw.begun);
        mediaPlayer[7] = MediaPlayer.create(audio_file.this, R.raw.potol);

        /*------------------------set the fruits items audio file----------------------------------------*/

        mediaPlayer[10] = MediaPlayer.create(audio_file.this, R.raw.mango);
        mediaPlayer[11] = MediaPlayer.create(audio_file.this, R.raw.lichi);
        mediaPlayer[12] = MediaPlayer.create(audio_file.this, R.raw.jackfriuts);
        mediaPlayer[13] = MediaPlayer.create(audio_file.this, R.raw.banana);
        mediaPlayer[14] = MediaPlayer.create(audio_file.this, R.raw.pineapple);
        mediaPlayer[15] = MediaPlayer.create(audio_file.this, R.raw.papye);
        mediaPlayer[16] = MediaPlayer.create(audio_file.this, R.raw.baokul);
        mediaPlayer[17] = MediaPlayer.create(audio_file.this, R.raw.lemon);

        /*------------------------set the fish items audio file----------------------------------------*/

        mediaPlayer[20] = MediaPlayer.create(audio_file.this, R.raw.rui);
        mediaPlayer[21] = MediaPlayer.create(audio_file.this, R.raw.catla);
        mediaPlayer[22] = MediaPlayer.create(audio_file.this, R.raw.mrigle);
        mediaPlayer[23] = MediaPlayer.create(audio_file.this, R.raw.shrimp);
        mediaPlayer[24] = MediaPlayer.create(audio_file.this, R.raw.koi);
        mediaPlayer[25] = MediaPlayer.create(audio_file.this, R.raw.magur);
        mediaPlayer[26] = MediaPlayer.create(audio_file.this, R.raw.kalbaush);
        mediaPlayer[27] = MediaPlayer.create(audio_file.this, R.raw.telapia);

        /*------------------------set the animal items audio file----------------------------------------*/

        mediaPlayer[30] = MediaPlayer.create(audio_file.this, R.raw.cow);
        mediaPlayer[31] = MediaPlayer.create(audio_file.this, R.raw.baffelo);
        mediaPlayer[32] = MediaPlayer.create(audio_file.this, R.raw.goat);
        mediaPlayer[33] = MediaPlayer.create(audio_file.this, R.raw.sheep);
        mediaPlayer[34] = MediaPlayer.create(audio_file.this, R.raw.chicken);
        mediaPlayer[35] = MediaPlayer.create(audio_file.this, R.raw.duck);
        mediaPlayer[36] = MediaPlayer.create(audio_file.this, R.raw.pegione);
        mediaPlayer[37] = MediaPlayer.create(audio_file.this, R.raw.koel);

    }
}
