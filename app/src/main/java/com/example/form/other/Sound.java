package com.example.form.other;


import android.media.MediaPlayer;

import com.example.form.FormGame;
import com.example.form.main.StaticField;
import com.example.test2.R;

import static com.example.form.main.StaticField.context;

public class Sound {

    public static void playSound(int sound){
        try{
            MediaPlayer mplayer = MediaPlayer.create(context,R.raw.combo);
            mplayer.start();
        }catch(Exception e){
           e.printStackTrace();
        }

    }
}
