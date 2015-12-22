package com.example.form;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.form.main.Main;
import com.example.form.main.StaticField;
import com.example.test2.R;


public class Menu extends Dialog implements OnClickListener{
    private int btnH;
    private int btnW;

    private RelativeLayout topP;
    private GridLayout centerP;
    private LinearLayout mainL;
    private Button contB;
    private Button soundB;
    private Button restartB;
    private Button facebookB;
    private Button vkB;
    private Dialog dialog;
    public static double heightCenter;


    public Menu(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context){
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.menu_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Size();
        createTop();
        createCenter(context);

        createButton();

        createParentL();

    }

    public void show(){
        dialog.show();
    }

    @Override
    public void onClick(View v) {

    }
    private void createTop() {
        topP = (RelativeLayout) dialog.findViewById(R.id.top);
        LinearLayout.LayoutParams topPanelParams = (LinearLayout.LayoutParams) topP.getLayoutParams();
        topPanelParams.height= (int) FormGame.getHeightTopPanel();
        topP.setLayoutParams(topPanelParams);
        topP.setAlpha(0);


    }

    private void createCenter(final Context context) {
        centerP = (GridLayout) dialog.findViewById(R.id.centerP);
        LinearLayout.LayoutParams centerPanelParams = (LinearLayout.LayoutParams) centerP.getLayoutParams();
        centerPanelParams.height= (int) FormGame.getHeightMainPanel();
        centerP.setLayoutParams(centerPanelParams);
        //centerP.setAlpha((float) 0.25);

        soundB =(Button) dialog.findViewById(R.id.soundB);
        restartB = (Button) dialog.findViewById(R.id.skinB);
        facebookB = (Button) dialog.findViewById(R.id.facebookB);
        vkB = (Button) dialog.findViewById(R.id.vkB);

        soundB.setLayoutParams(butParams(1));

        restartB.setLayoutParams(butParams(2));

        vkB.setLayoutParams(butParams(3));
        vkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView vk = new WebView(context);
                vk.getSettings().setJavaScriptEnabled(true);
                vk.loadUrl("http://vk.com/");
            }
        });


        facebookB.setLayoutParams(butParams(4));
        facebookB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView facebook = new WebView(context);
                facebook.getSettings().setJavaScriptEnabled(true);
                facebook.loadUrl("https://www.facebook.com/");
            }
        });



    }

    private void createButton(){
        contB = (Button) dialog.findViewById(R.id.continueButton);
        LinearLayout.LayoutParams contParam = (LinearLayout.LayoutParams) contB.getLayoutParams();
        contParam.height= (int) FormGame.getHeightBottmPanel();
        contB.setTextSize((float) (FormGame.getHeightBottmPanel() * 0.1));
        contB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticField.start) {
                    StaticField.start = false;
                }
                Main.field.pause();
                dialog.dismiss();
            }
        });
    }

    private void createParentL(){
        mainL = (LinearLayout) dialog.findViewById(R.id.menuMain);
    }

    private void Size(){
        heightCenter= FormGame.getHeightMainPanel();

        btnH= (int) (Math.round(heightCenter / 2)-30);
        btnW= (Math.round(FormGame.getWidth() / 2)-30);
    }

    private GridLayout.LayoutParams butParams(int number){
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params = new GridLayout.LayoutParams();
        params.height=btnH;
        params.width=btnW;
        if (number==1){
            params.setMargins(15,20,10,15);
        }
        else if (number==2){
            params.setMargins(10,20,10,15);
        }
        else if (number==3){
            params.setMargins(15,0,10,0);
        }
        else if (number==4){
            params.setMargins(10, 0, 0, 0);
        }

        return params;
    }
}