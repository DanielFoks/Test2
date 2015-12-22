package com.example.form.other;

import android.content.Context;
import android.content.Intent;

import com.example.form.FormGame;
import com.example.form.main.Main;
import com.example.form.main.StaticField;

public class Points {
    private static int points = 0;
    private static int x = 1;
    private static int maxPoints = 0;

    public static void addPoints(int points) {
        Points.points = (Points.points + points * x);
        setMaxPoints();
        if(Points.points < 0){
            Main.field.restart();
            StaticField.start = true;


        }
    }

    public static int getPoints() {
        return Points.points;
    }

    public static void setX(int x) {
        Points.x = x;
    }

    public static int getX() {
        return x;
    }

    public static int getMaxPoints() {
        return maxPoints;
    }

    public static void restart(){
        x = 1;
        points = 0;
        maxPoints = 0;
    }

    private static void setMaxPoints(){
        if (points > maxPoints){
            maxPoints = points;
        }
        if(maxPoints > StaticField.record){
            StaticField.record = maxPoints;
        }
    }
}
