package com.example.form.square;


import android.graphics.Color;
import android.os.Handler;

import com.example.form.MyApplication;
import com.example.form.main.StaticField;
import com.example.form.other.Position;

import java.util.Date;
import java.util.TimerTask;

public abstract class Square {
    private int row;
    private int column;
    private int time;
    private int point;
    protected int icon;

    private MyTimer timerTime;
    protected Date date;
    private Handler handler;

    public Square(Position pos, int time) {
        this.row = pos.getRow();
        this.column = pos.getColumn();
        this.time = time;
        point = (int) (Math.random() * 200) - 100;
        defaultOptions();
    }

    public Square(Position pos, int time, int minPoint, int maxPoint) {
        this.row = pos.getRow();
        this.column = pos.getColumn();
        this.time = time;
        point = (int) (Math.random() * (maxPoint - minPoint + 1)) + minPoint;
        defaultOptions();
    }

    private void defaultOptions() {
        date = new Date();
        startCountTime();
        icon();
    }

    private void startCountTime() {
        handler = new Handler(MyApplication.getAppContext().getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time = 0;
            }
        }, time);
    }

    public void pause() {
        handler.removeCallbacksAndMessages(null);
    }

    public void unPause(Date currTime) {
        long delay = StaticField.speed - (currTime.getTime() - date.getTime());
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time = 0;
            }
        }, delay);
    }

    public int getTime() {
        return time;
    }

    public String getString() {
        return "";
    }

    public int getIcon() {
        return icon;
    }

    public int getPoint() {
        return point;
    }

    public abstract void press();

    public abstract void remove();

    public abstract void icon();
}
