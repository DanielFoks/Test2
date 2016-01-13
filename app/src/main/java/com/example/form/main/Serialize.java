package com.example.form.main;

import java.io.Serializable;

public class Serialize implements Serializable {
    static final long SerialVersionUID = 100000001;
    private int record;

    Serialize() {
        record = StaticField.record;
    }

    public void sendData() {
        StaticField.record = record;
    }
}
