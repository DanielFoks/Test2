package com.example.form.other;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return getClass().getName() + "[row=" + row + ", column=" + column + "]";
    }
}
