package com.example.form;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.form.field.Field;
import com.example.form.main.Main;
import com.example.form.other.Points;
import com.example.form.square.MyTimer;
import com.example.test2.R;

import java.util.Timer;
import java.util.TimerTask;

public class FormGame extends Activity {


    private LinearLayout parentContainer;
    private RelativeLayout panelTop;
    private RelativeLayout panelBottom;
    private GridLayout panelCenter;

    private Main main = new Main();
    private int size = main.sizeField;
    private Field field = main.field;
    private Button[][] buttonMass = new Button[size][size];

    private Button buttonMenu;

    private static double heightTopPanel;
    private static double heightBottomPanel;
    private static double heightMainPanel;
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

    private Color color;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        formCreate();
        addButton();
        repaintForm();
        menu();
        openMenu();

        //createParentContainer();
        //setContentView(parentContainer);

    }

    private void menu() {
        buttonMenu =(Button) findViewById(R.id.menuButton);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }

    private void openMenu(){
        field.pause();
        final Menu menu = new Menu(this);
        menu.show();
    }

    private void addButton() {
        panelCenter = (GridLayout) findViewById(R.id.mainPanel);
        LinearLayout.LayoutParams mainPanelParams = (LinearLayout.LayoutParams) panelCenter.getLayoutParams();
        mainPanelParams.height= (int) heightMainPanel;
        panelCenter.setLayoutParams(mainPanelParams);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttonMass[i][j]=new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height= (int) btnH;
                params.width= (int) btnW;
                params.setMargins(0, 0, 0, marginSizeH);
                if(j==0){
                    params.setMargins(marginSizeW, 0, marginSizeW, marginSizeH);
                }
                if(i==0){
                    params.setMargins(0,marginSizeH,marginSizeW,marginSizeH);
                }
                if(i==0 && j==0){
                    params.setMargins(marginSizeW,marginSizeH,marginSizeW,marginSizeH);
                }
                panelCenter.addView(buttonMass[i][j], params);
                buttonListener(i,j);
            }

        }
    }


    private void createTopP()
    {
        panelTop = (RelativeLayout) findViewById(R.id.topPanel);

        LinearLayout.LayoutParams topPanelParams = (LinearLayout.LayoutParams) panelTop.getLayoutParams();
        topPanelParams.height= (int) heightTopPanel;
        panelTop.setLayoutParams(topPanelParams);

        /*customTextView(score);
        customTextView(xView);*/

    }
    private void createBottomP()
    {
        panelBottom = (RelativeLayout) findViewById(R.id.bottomPanel);
        LinearLayout.LayoutParams bottomPanelParams = (LinearLayout.LayoutParams) panelBottom.getLayoutParams();
        bottomPanelParams.height=(int) getHeightBottmPanel()+5;
        panelBottom.setLayoutParams(bottomPanelParams);

        //customTextView(bestScore);
    }
    private void createParentContainer()
    {
        parentContainer = (LinearLayout) findViewById(R.id.parentPanel);
        parentContainer.setDrawingCacheEnabled(true);
    }
    private void setSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        int height = size.y;

        heightTopPanel= (height*0.10);
        heightBottomPanel= (height*0.25);

        heightMainPanel=height-(heightTopPanel+heightBottomPanel);

        marginSizeH= (int) Math.round((heightMainPanel / 400) * 5);

        marginSizeW= Math.round((width / 400) * 5);

        double MainPanelWorkHeight=heightMainPanel-(marginSizeH*6);

        double MainPanelWorkWidth=width-(marginSizeW*6);

        btnH=MainPanelWorkHeight/5;
        btnW=MainPanelWorkWidth/5;

        System.err.println(FormGame.getHeightTopPanel()+" "+FormGame.getHeightMainPanel()+" "+FormGame.getHeightBottmPanel()+"FORMGAME");


    }

    private void buttonListener(final int row, final int col) {
      buttonMass[row][col].setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (field.getArraySquare()[row][col] != null) {
                  field.removeSquare(row, col);
              }
          }
      });
    }


    private void customTextView(View view){
        if(view==score){
            view = findViewById(R.id.score);
            textHeigh=heightTopPanel*0.7;
        }if(view==xView){
            view = findViewById(R.id.labelX);
            textHeigh=heightTopPanel*0.7;
        }if(view==bestScore){
            view =  findViewById(R.id.bestScore);
            textHeigh=heightBottomPanel*0.7;
        }


        RelativeLayout.LayoutParams viewParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        viewParams.height= (int) textHeigh;
        view.setLayoutParams(viewParams);

    }

    public static double getHeightMainPanel(){
        return heightMainPanel;
    }
    public static double getHeightTopPanel(){
        return heightTopPanel;
    }
    public static double getHeightBottmPanel(){
        return heightBottomPanel;
    }
    public static int getWidth(){
        return width;
    }

    private void formCreate(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setSize();
        createBottomP();
        createTopP();

        labelX = (TextView) findViewById(R.id.labelX);
        score = (TextView) findViewById(R.id.score);

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

                        score.setText(String.valueOf(Points.getPoints()) + " / ");
                        score.setText(score.getText() + String.valueOf(Points.getMaxPoints()));


                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                if (field.getArraySquare()[i][j] != null) {
                                    buttonMass[i][j].setVisibility(View.VISIBLE);
                                    buttonMass[i][j].setText(String.valueOf(field.getSquareString(i, j)));
                                    buttonMass[i][j].setBackgroundResource(field.getSquareIcon(i, j));
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
}