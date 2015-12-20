package com.example.form.square;

import android.graphics.Color;

import com.example.form.other.Points;
import com.example.form.other.Position;

public class SquareNormal extends Square{
    public SquareNormal(Position pos, int time) {
        super(pos, time);
    }

    public SquareNormal(Position pos, int time, int minPoint, int maxPoint) {
        super(pos, time, minPoint, maxPoint);
    }

    public String getString() {
        return String.valueOf(getPoint());
    }

    public void press() {
       // Sound.playSound("sounds/SquareNorm.wav");
        Points.addPoints(getPoint());
    }

    public void remove() {
        if(getPoint() > 0) {
            Points.addPoints(-getPoint());
            Points.setX(1);
        }
    }

    @Override
    public void icon() {
    }
}
