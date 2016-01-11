package com.example.form;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.form.field.Field;
import com.example.form.main.Save;
import com.example.form.main.StaticField;
import com.example.form.other.Points;
import com.example.form.square.MyTimer;
import com.example.test2.R;


import java.util.TimerTask;

public class FormGame extends Activity {


    private LinearLayout parentContainer;
    private RelativeLayout panelTop;
    private RelativeLayout panelBottom;
    private GridLayout panelCenter;

    private int size = StaticField.sizeField;
    private Field field = StaticField.field;
    private Button[][] buttonMass = new Button[size][size];

    private Button buttonMenu;

    private static double heightTopPanel;
    private static double heightBottomPanel;
    private static double heightMainPanel;
    private static double widthMainPanel;
    private double btnH;
    private double btnW;
    private double textHeigh;
    private int marginSizeW;
    private int marginSizeH;
    private static int width;

    private TextView score;
    private TextView xView;
    private TextView bestScore;
    private TextView labelX;


    private AudioManager audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        formCreate();
        addButton();
        repaintForm();
        menu();


        Save.load(this);
    }


    private void menu() {
        buttonMenu = (Button) findViewById(R.id.menuButton);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) buttonMenu.getLayoutParams();
        params.height = (int) (getHeightTopPanel() * 0.70);
        params.width = (int) (getHeightTopPanel() * 0.70);
        buttonMenu.setLayoutParams(params);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }

    public static void openMenu() {
        StaticField.field.pause();
        Handler mainHandler = new Handler(MyApplication.getAppContext().getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                StaticField.menu.show();
            }
        };
        mainHandler.post(myRunnable);
    }

    private void addButton() {

        LinearLayout.LayoutParams mainPanelParams = (LinearLayout.LayoutParams) panelCenter.getLayoutParams();
        mainPanelParams.height = (int) heightMainPanel;
        mainPanelParams.width = (int) widthMainPanel;
        mainPanelParams.gravity = Gravity.CENTER;
        panelCenter.setLayoutParams(mainPanelParams);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttonMass[i][j] = new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = (int) btnH;
                params.width = (int) btnW;
                params.setMargins(0, 0, 0, marginSizeH);
                buttonMass[i][j].setTextColor(Color.rgb(174, 174, 174));
                buttonMass[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                if (j == 0) {
                    params.setMargins(marginSizeW, 0, marginSizeW, marginSizeH);
                }
                if (i == 0) {
                    params.setMargins(0, marginSizeH, marginSizeW, marginSizeH);
                }
                if (i == 0 && j == 0) {
                    params.setMargins(marginSizeW, marginSizeH, marginSizeW, marginSizeH);
                }
                panelCenter.addView(buttonMass[i][j], params);
                buttonListener(i, j);
            }

        }
    }


    private void createTopP() {
        panelTop = (RelativeLayout) findViewById(R.id.topPanel);

        LinearLayout.LayoutParams topPanelParams = (LinearLayout.LayoutParams) panelTop.getLayoutParams();
        topPanelParams.height = (int) heightTopPanel;
        panelTop.setLayoutParams(topPanelParams);

    }

    private void createBottomP() {
        panelBottom = (RelativeLayout) findViewById(R.id.bottomPanel);
        LinearLayout.LayoutParams bottomPanelParams = (LinearLayout.LayoutParams) panelBottom.getLayoutParams();
        bottomPanelParams.height = (int) getHeightBottmPanel() + 5;
        panelBottom.setLayoutParams(bottomPanelParams);

    }


    private void setSize() {
        panelCenter = (GridLayout) findViewById(R.id.mainPanel);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        int height = size.y;

        heightTopPanel = (height * 0.10);
        heightBottomPanel = (height * 0.25);

        heightMainPanel = height - (heightTopPanel + heightBottomPanel);

        marginSizeH = (int) Math.round((heightMainPanel / 400) * 5);

        marginSizeW = Math.round((width / 400) * 5);

        double MainPanelWorkHeight = heightMainPanel - (marginSizeH * 6);

        double MainPanelWorkWidth = width - (marginSizeW * 6);

        btnH = MainPanelWorkHeight / 5;
        btnW = MainPanelWorkWidth / 5;

        heightMainPanel = (marginSizeH * 6) + ((int) btnH) * 5;

        widthMainPanel = ((marginSizeW * 6) + ((int) btnW) * 5);


        if (widthMainPanel < width) {
            btnW = 141;
            marginSizeW = 10;
        }

        if (marginSizeW == 0) {
            btnW = 58;
            marginSizeW = 5;
            panelCenter.setBackgroundResource(R.drawable.grid2);
        }


        System.err.println((int) heightMainPanel + "-Высота панели " + marginSizeH + "-отступ по высоте " + marginSizeW + "-отступ по ширине " + width + "-ширина панели");
        System.err.println((int) btnH + "-высота кнопки " + (int) btnW + "-ширина кнопки");
        System.err.println((heightMainPanel / 400) * 5 + "    " + marginSizeH);
        System.err.println("------------------" + ((marginSizeH * 6) + ((int) btnH) * 5));
        System.err.println(((marginSizeW * 6) + ((int) btnW) * 5));

    }

    private void buttonListener(final int row, final int col) {
        buttonMass[row][col].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (field.getArraySquare()[row][col] != null) {
                    field.removeSquare(row, col);
                }
                return false;
            }
        });
    }

    public static double getHeightMainPanel() {
        return heightMainPanel;
    }

    public static double getHeightTopPanel() {
        return heightTopPanel;
    }

    public static double getHeightBottmPanel() {
        return heightBottomPanel;
    }

    public static int getWidth() {
        return width;
    }

    private void formCreate() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setSize();
        createBottomP();
        createTopP();

        labelX = (TextView) findViewById(R.id.labelX);
        labelX.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        score = (TextView) findViewById(R.id.score);
        score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        bestScore = (TextView) findViewById(R.id.bestScore);

        StaticField.menu = new Menu(this);
        if (!StaticField.menu.isShowing()) {
            openMenu();
        }
    }

    private void repaintForm() {
        final MyTimer timerRepaint = new MyTimer(100);
        timerRepaint.schedules(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        labelX.setText(Points.getX() + " ");

                        score.setText(String.valueOf(Points.getPoints()));
                        bestScore.setText("Best Score:" + StaticField.record + "\n" + "\n" + "Best Score In This Game:" + String.valueOf(Points.getMaxPoints()));
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                if (field.getArraySquare()[i][j] != null) {
                                    buttonMass[i][j].setBackgroundResource(field.getSquareIcon(i, j));
                                    buttonMass[i][j].setVisibility(View.VISIBLE);
                                    buttonMass[i][j].setText(String.valueOf(field.getSquareString(i, j)));
                                } else {
                                    buttonMass[i][j].setVisibility(View.INVISIBLE);
                                }

                            }
                        }

                    }
                });
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (StaticField.menu.isShowing()) {
/*menu.dismiss();*/
        } else {
            openMenu();
        }
    }
}