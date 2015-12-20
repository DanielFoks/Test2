package  com.example.form.main;


import com.example.form.FormGame;
import com.example.form.field.Field;
import com.example.form.square.Square;

public class StaticField{
    public static boolean pause = false;
    public static boolean combo = false;
    public static boolean start = true;
    public static int speed = 1500;
    public static final int StartSpeed = 1500;
    public static int record = 0;
    public static int sound;
    public static int sizeField = 5;
    public static FormGame form;
    private static Square[][] arraySquare;
    public static Field field;
}
