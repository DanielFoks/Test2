package com.example.form.square;

import com.example.form.FormGame;
import com.example.form.main.Main;
import com.example.form.main.StaticField;
import com.example.form.other.Position;
import com.example.test2.R;

import java.text.Normalizer;

public class SquareLose extends Square {

    public SquareLose(Position pos, int time) {
        super(pos, time);
    }

    @Override
    public void press() {
        StaticField.field.restart();
        StaticField.start = true;
        FormGame.openMenu();
    }

    @Override
    public void remove() {

    }

    @Override
    public void icon() {
        icon = R.drawable.squarelose5;
    }
}
