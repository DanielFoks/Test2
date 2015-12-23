package com.example.form.other;


import android.media.MediaPlayer;

import com.example.form.main.StaticField;

public class Sound {
private static MediaPlayer mp;
    static boolean playerStart = false;
    public static void playSound(final int sound){
        if(StaticField.enableSound){
            new Thread(){
                public void run(){
                    if(!playerStart){
                        playerStart=true;
                        mp = MediaPlayer.create(StaticField.context, sound);
                        mp.start();
                    }else{
                        mp.stop();
                        mp = MediaPlayer.create(StaticField.context, sound);
                        mp.start();
                    }

                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            mp.release();
                            playerStart=false;
                        }
                    });
                }
            }.start();
        }
}}
