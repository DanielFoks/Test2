package com.example.form.main;

import com.example.form.field.Field;

import java.io.FileNotFoundException;

import java.io.IOException;

import android.content.Context;


import java.io.*;

public class Main{
    public static void save(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput("data.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();
            oos.writeObject(serialize);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(Context context){
        FileInputStream fis = null;

        try {
            fis = context.openFileInput("data.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream oin = null;

        try {
            oin = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Serialize serialize = (Serialize) oin.readObject();
            serialize.sendData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
