package com.example.form.square;

import com.example.test2.R;

import com.example.form.main.StaticField;
import com.example.form.other.Position;

public class SquareCombo extends Square{

    public SquareCombo(Position pos, int time) {
        super(pos, time);
    }


    public void press() {
        StaticField.combo = true;
        //Sound.playSound("sounds/SquareCombo.wav");
    }

    public void remove() {

    }

    @Override
    public void icon() {
        icon = R.drawable.squarebonus5;
    }
}
