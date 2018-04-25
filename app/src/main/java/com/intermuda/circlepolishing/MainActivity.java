package com.intermuda.circlepolishing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int screenWidth;
    private int screenHeight;
    String
            font_name = "";
    functions fun;

    Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        fun = new functions();

        font_name = fun.font_name;


//        SharedPreferences settings = getApplicationContext().getSharedPreferences("homeScore", 0);
//        fun.u_name =  settings.getString("homeScore","");
//        functions.u_name =  settings.getString("homeScore","");

        tf = Typeface.createFromAsset(getAssets(),font_name );
        set_content();

    }
    private void set_content()
    {

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////TAB BAR
        //////////////////////////////////////////////////////////////////////






        RelativeLayout lay_top_menu_right= (RelativeLayout) findViewById(R.id.lay_top_menu_right);

        ConstraintLayout.LayoutParams lp_lay_top_menu_right = (ConstraintLayout.LayoutParams) lay_top_menu_right.getLayoutParams();
        lp_lay_top_menu_right.width = (int)(screenWidth*0.23);
        lp_lay_top_menu_right.height = (int)(screenHeight*0.045);

        //new ConstraintLayout.LayoutParams((int)(screenWidth*0.1),(int)(screenHeight*0.1));
        lay_top_menu_right.setLayoutParams(lp_lay_top_menu_right);

        ImageView img_coin = (ImageView) findViewById(R.id.img_coin);
        ConstraintLayout.LayoutParams lp_default = (ConstraintLayout.LayoutParams) img_coin.getLayoutParams();
        lp_default.width = (int)(screenWidth*0.067);
        lp_default.height = (int)(screenHeight*0.07);
        img_coin.setLayoutParams(lp_default);

        ImageView img_add_coin = (ImageView) findViewById(R.id.img_add_coin);
        ConstraintLayout.LayoutParams lp_default1 = (ConstraintLayout.LayoutParams) img_add_coin.getLayoutParams();
        lp_default1.width = (int)(screenWidth*0.04);
        lp_default1.height = (int)(screenHeight*0.04);
        img_add_coin.setLayoutParams(lp_default1);


        TextView txt_coin = (TextView) findViewById(R.id.txt_coin);
        txt_coin.setTypeface(tf);
        txt_coin.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.046));


        RelativeLayout lay_top_menu_left1= (RelativeLayout) findViewById(R.id.lay_top_menu_left);

        ConstraintLayout.LayoutParams lp_lay_top_menu_left1 = (ConstraintLayout.LayoutParams) lay_top_menu_left1.getLayoutParams();
        lp_lay_top_menu_left1.width = (int)(screenWidth*0.23);
        lp_lay_top_menu_left1.height = (int)(screenHeight*0.045);
        lay_top_menu_left1.setLayoutParams(lp_lay_top_menu_left1);

        ImageView img_bell = (ImageView) findViewById(R.id.img_bell);
        ConstraintLayout.LayoutParams lp_default3 = (ConstraintLayout.LayoutParams) img_bell.getLayoutParams();
        lp_default3.width = (int)(screenWidth*0.07);
        lp_default3.height = (int)(screenHeight*0.08);
        img_bell.setLayoutParams(lp_default3);



        TextView txt_notification = (TextView) findViewById(R.id.txt_notification);
        txt_notification.setTypeface(tf);
        txt_notification.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.046));





        ////////////////////////////////////////////////////////////////////// End OF
        ////////////////////////////////////////////////////////////////////// TAB BAR
        //////////////////////////////////////////////////////////////////////



        ImageView img_play = findViewById(R.id.img_play);
        LinearLayout.LayoutParams lp_img_play = new LinearLayout.LayoutParams((int)(screenWidth*.58),(int)(screenHeight*.58));
        img_play.setLayoutParams(lp_img_play);











    }


    public void clk_play(View view) {
        startActivity(new Intent(this,GameBoard.class));
    }
}
