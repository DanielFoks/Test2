package com.example.form.square;

import com.example.form.FormGame;
import com.example.form.main.StaticField;
import com.example.form.other.Position;
import com.example.form.other.Sound;
import com.example.test2.R;

public class SquareLose extends Square {

    public SquareLose(Position pos, int time) {
        super(pos, time);
    }

    @Override
    public void press() {
        Sound.playSound(R.raw.lose);
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
