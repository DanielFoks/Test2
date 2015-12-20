package com.example.form.square;

import java.util.Timer;
import java.util.TimerTask;


public class MyTimer extends Timer {
    private long delay;

    public MyTimer(long delay){
        this.delay=delay;
    }

    public void schedules(TimerTask task) {
        super.schedule(task, this.delay,this.delay);
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;

    }
}
