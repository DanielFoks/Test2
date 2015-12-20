package com.example.form.main;

import java.io.Serializable;

public class Serialize implements Serializable {

    private int record;

    Serialize() {
        record = StaticField.record;
    }

    public void sendData(){
        StaticField.record = record;
    }
}
