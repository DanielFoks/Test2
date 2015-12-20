package com.example.form.square;


import com.example.form.other.Position;

import java.util.TimerTask;

public abstract class Square {
    private int row;
    private int column;
    private int time;
    private int point;
    protected int icon;

    private MyTimer timerTime;
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
        startCountTime();
        icon();
    }

    private void startCountTime() {
        time = time - 100;
        timerTime =new MyTimer(10);
        timerTime.schedules(new TimerTask() {
            @Override
            public void run() {
                if (time > 0) {
                    time = time - 10;
                }
            }
        });}

    public void pause(){
        timerTime.cancel();
    }

    public void unPause(){
        timerTime = new MyTimer(10);
        timerTime.schedules(new TimerTask() {
            @Override
            public void run() {
                if (time > 0) {
                    time = time - 10;
                }
            }
        });
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
