package com.example.form;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
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
    private Button skinB;
    private Button facebookB;
    private Button vkB;
    private Dialog dialog;
    public static double heightCenter;

    private int ASDASs;

    private GridLayout.LayoutParams butParams;

    public Menu(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context){
        dialog = new Dialog(context);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.menu_layout);
        dialog.getWindow().setBackgroundDrawable(null);
        Size();
        createTop();
        createCenter();

        createButton();

        createParentL();

    }

    public void show(){
        dialog.show();
    }

    @Override
    public void onClick(View v) {

    }
    private void createTop(){
        topP = (RelativeLayout) dialog.findViewById(R.id.top);
        LinearLayout.LayoutParams topPanelParams = (LinearLayout.LayoutParams) topP.getLayoutParams();
        topPanelParams.height= (int) FormGame.getHeightTopPanel();
        topP.setLayoutParams(topPanelParams);

    }

    private void createCenter(){
        centerP = (GridLayout) dialog.findViewById(R.id.centerP);
        LinearLayout.LayoutParams centerPanelParams = (LinearLayout.LayoutParams) centerP.getLayoutParams();
        centerPanelParams.height= (int) FormGame.getHeightMainPanel();
        centerP.setLayoutParams(centerPanelParams);
        centerP.setAlpha((float) 0.7);

        soundB =(Button) dialog.findViewById(R.id.soundB);
        skinB = (Button) dialog.findViewById(R.id.skinB);
        facebookB = (Button) dialog.findViewById(R.id.facebookB);
        vkB = (Button) dialog.findViewById(R.id.vkB);

        createGridlayoutP();
        soundB.setLayoutParams(butParams);

        createGridlayoutP();
        skinB.setLayoutParams(butParams);

        createGridlayoutP();
        facebookB.setLayoutParams(butParams);

        createGridlayoutP();
        vkB.setLayoutParams(butParams);


    }

    private void createButton(){
        contB = (Button) dialog.findViewById(R.id.continueButton);
        LinearLayout.LayoutParams contParam = (LinearLayout.LayoutParams) contB.getLayoutParams();
        contParam.height= (int) FormGame.getHeightBottmPanel();
        contB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticField.start){
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

    private void createGridlayoutP(){
        butParams = new GridLayout.LayoutParams();
        butParams.height=btnH;
        butParams.width=btnW;
        butParams.setMargins(10,10,10,10);
    }
}