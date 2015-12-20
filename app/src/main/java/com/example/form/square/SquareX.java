package com.example.form.square;

import com.example.form.other.Points;
import com.example.form.other.Position;
import com.example.test2.R;


public class SquareX extends Square {
    public SquareX(Position pos, int time) {
        super(pos, time);
    }

    @Override
    public void press() {
      //  Sound.playSound("sounds/SquareX.wav");
        Points.setX(Points.getX() + 1);
    }

    @Override
    public void remove() {

    }

    @Override
    public void icon() {
        icon = R.drawable.squarex5;
    }
}
