package com.example.form.square;

import com.example.form.main.Main;
import com.example.form.main.StaticField;
import com.example.form.other.Position;
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
        //Sound.playSound("sounds/SquareLow.wav");
        StaticField.field.timerMainSlower(200);
    }

    @Override
    public void remove() {

    }

    @Override
    public void icon() {
        icon = R.drawable.squarelose5;
    }
}
