package com.example.form.square;

import com.example.form.main.StaticField;
import com.example.form.other.Position;
import com.example.form.other.Sound;
import com.example.test2.R;


public class SquareLow extends Square {
    public SquareLow(Position pos, int time) {
        super(pos, time);
    }

    public SquareLow(Position pos, int time, int minPoint, int maxPoint) {
        super(pos, time, minPoint, maxPoint);
    }

    @Override
    public void press() {
        Sound.playSound(R.raw.slow);
        StaticField.field.timerMainEditTime(-200);
    }

    @Override
    public void remove() {

    }

    @Override
    public void icon() {
        icon = R.drawable.squareslow5;
    }
}
