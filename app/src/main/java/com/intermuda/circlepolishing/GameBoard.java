package com.intermuda.circlepolishing;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameBoard extends AppCompatActivity  {
    ImageView imageview;
    ImageView imageview1;
    private int screenWidth;
    private int screenHeight;
    RelativeLayout relativelayout ;
    RelativeLayout lay_stars;

    // View[] onTouchView = new View[2000000];
    String
            font_name = "";
    functions fun;

    String
            target_word = "";
    String
            tip_of_target_word = "";

    Timer tim;

    Typeface tf;
    int img_count = 1;

    ImageView[][][] img_circles = new ImageView[8][65][65];
    int[] img_color_circle = new int[16400];

    int
            img_size = 0;


    int[] ans_box = new int[17];
    int[] ans_box_status = new int[17];

    double stars_size=0;
    private double dec_count=1.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        // Circle circle = (Circle) findViewById(R.id.circle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        fun = new functions();

        for(int i=1;i<=16;i++)
        {
            ans_box[i]=0;
            ans_box_status[i]=0;

        }
        font_name = fun.font_name;
        target_word = "google";
        tip_of_target_word ="It's An IT Company";
        relativelayout =findViewById(R.id.lay_contain_image);


        tf = Typeface.createFromAsset(getAssets(),font_name );
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        imageview1 = findViewById(R.id.imageView);

        lay_stars=  findViewById(R.id.lay10);
        stars_size =(screenWidth*.32);



        RelativeLayout lay_containt_image = findViewById(R.id.lay1);
        LinearLayout.LayoutParams lp_lay_containt_image = (LinearLayout.LayoutParams) lay_containt_image.getLayoutParams();
        img_size = (int) (screenWidth * .75);
        lp_lay_containt_image.width = (img_size);
        lp_lay_containt_image.height = img_size;
        lp_lay_containt_image.setMarginStart((int) (screenWidth * .13));
        lp_lay_containt_image.topMargin = (int) (screenWidth * .01);


        lay_containt_image.setLayoutParams(lp_lay_containt_image);

        RelativeLayout.LayoutParams lp_img = (RelativeLayout.LayoutParams) imageview1.getLayoutParams();
        lp_img.width = (img_size);
        lp_img.height = img_size;
        imageview1.setLayoutParams(lp_img);

        set_content_size();

        set_boxes();


        on_touch_disabled=true;
        LinearLayout lay_main = findViewById(R.id.lay_main);
        fun.enableDisableView(lay_main,false);
        Timer tim1 =new Timer("draw");
        tim1.milles=2000;
        tim1.start();



    }

    private void set_boxes() {
        for ( int i=1;i<=16;i++)
        {
            lay_ans_box[i].setVisibility(View.GONE);
        }
        for ( int i=1;i<=target_word.length();i++)
        {
            lay_ans_box[i].setVisibility(View.VISIBLE);
        }
        for ( int i=1;i<=16;i++)
        {
            txt_ans_box[i].setText("");
        }
        target_word = target_word.toUpperCase();
        int[] pos_char = new int[17];
        for(int i=1;i<=target_word.length();i++)
        {
            boolean flag = true;
            while(flag) {
                flag=false;
                pos_char[i] = new Random().nextInt(20)+1;
                for (int j = 1; j < i; j++) {
                        if(pos_char[j]==pos_char[i])
                        {
                            flag=true;
                            break;
                        }
                }
            }
        }
        for(int i=1;i<=16;i++)
        {
            Log.d("majid",String.valueOf(pos_char[i])+"-");
        }
        for(int i =1;i<=21;i++)
        {
            int flag=0;
            for(int j=1;j<=target_word.length();j++)
            {
                if(i==pos_char[j])
                    flag=j;
            }
            if(flag>0)
            {
                txt_random_box[i].setText(String.valueOf(target_word.charAt(flag-1)));
            }
            else
            {
                txt_random_box[i].setText(Character.toString ((char) (new Random().nextInt(26)+65)));
            }

        }


    }

    LinearLayout[] lay_ans_box = new LinearLayout[19];
    LinearLayout[] lay_random_box = new LinearLayout[22];
    TextView[] txt_ans_box = new TextView[19];
    TextView[] txt_random_box = new TextView[22];

    private void set_content_size()
    {
        int
            a=0;
        int
            txt_size =(int) (screenWidth * 0.08);

        LinearLayout.LayoutParams lp_lay_ans= new LinearLayout.LayoutParams((int)(screenWidth*.112),(int)(screenWidth*.112));
        lp_lay_ans.setMarginEnd((int) (screenWidth * 0.007));;
        a=1; lay_ans_box[a] = findViewById(R.id.lay_ans_box1); txt_ans_box[a] = findViewById(R.id.txt_ans_box1);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=2; lay_ans_box[a] = findViewById(R.id.lay_ans_box2); txt_ans_box[a] = findViewById(R.id.txt_ans_box2);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=3; lay_ans_box[a] = findViewById(R.id.lay_ans_box3); txt_ans_box[a] = findViewById(R.id.txt_ans_box3);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=4; lay_ans_box[a] = findViewById(R.id.lay_ans_box4); txt_ans_box[a] = findViewById(R.id.txt_ans_box4);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=5; lay_ans_box[a] = findViewById(R.id.lay_ans_box5); txt_ans_box[a] = findViewById(R.id.txt_ans_box5);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=6; lay_ans_box[a] = findViewById(R.id.lay_ans_box6); txt_ans_box[a] = findViewById(R.id.txt_ans_box6);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=7; lay_ans_box[a] = findViewById(R.id.lay_ans_box7); txt_ans_box[a] = findViewById(R.id.txt_ans_box7);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=8; lay_ans_box[a] = findViewById(R.id.lay_ans_box8); txt_ans_box[a] = findViewById(R.id.txt_ans_box8);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=9; lay_ans_box[a] = findViewById(R.id.lay_ans_box9); txt_ans_box[a] = findViewById(R.id.txt_ans_box9);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=10; lay_ans_box[a] = findViewById(R.id.lay_ans_box10); txt_ans_box[a] = findViewById(R.id.txt_ans_box10);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=11; lay_ans_box[a] = findViewById(R.id.lay_ans_box11); txt_ans_box[a] = findViewById(R.id.txt_ans_box11);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=12; lay_ans_box[a] = findViewById(R.id.lay_ans_box12); txt_ans_box[a] = findViewById(R.id.txt_ans_box12);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=13; lay_ans_box[a] = findViewById(R.id.lay_ans_box13); txt_ans_box[a] = findViewById(R.id.txt_ans_box13);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=14; lay_ans_box[a] = findViewById(R.id.lay_ans_box14); txt_ans_box[a] = findViewById(R.id.txt_ans_box14);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=15; lay_ans_box[a] = findViewById(R.id.lay_ans_box15); txt_ans_box[a] = findViewById(R.id.txt_ans_box15);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        a=16; lay_ans_box[a] = findViewById(R.id.lay_ans_box16); txt_ans_box[a] = findViewById(R.id.txt_ans_box16);lay_ans_box[a].setLayoutParams(lp_lay_ans);txt_ans_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_ans_box[a].setTypeface(tf);
        txt_size =(int) (screenWidth * 0.067);
        LinearLayout.LayoutParams lp_lay_random= new LinearLayout.LayoutParams((int)(screenWidth*.098),(int)(screenWidth*.098));
        lp_lay_random.setMarginEnd((int) (screenWidth * 0.007));

        a=1; lay_random_box[a] = findViewById(R.id.lay_random_box1); txt_random_box[a] = findViewById(R.id.txt_random_box1);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=2; lay_random_box[a] = findViewById(R.id.lay_random_box2); txt_random_box[a] = findViewById(R.id.txt_random_box2);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=3; lay_random_box[a] = findViewById(R.id.lay_random_box3); txt_random_box[a] = findViewById(R.id.txt_random_box3);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=4; lay_random_box[a] = findViewById(R.id.lay_random_box4); txt_random_box[a] = findViewById(R.id.txt_random_box4);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=5; lay_random_box[a] = findViewById(R.id.lay_random_box5); txt_random_box[a] = findViewById(R.id.txt_random_box5);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=6; lay_random_box[a] = findViewById(R.id.lay_random_box6); txt_random_box[a] = findViewById(R.id.txt_random_box6);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=7; lay_random_box[a] = findViewById(R.id.lay_random_box7); txt_random_box[a] = findViewById(R.id.txt_random_box7);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=8; lay_random_box[a] = findViewById(R.id.lay_random_box8); txt_random_box[a] = findViewById(R.id.txt_random_box8);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=9; lay_random_box[a] = findViewById(R.id.lay_random_box9); txt_random_box[a] = findViewById(R.id.txt_random_box9);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=10; lay_random_box[a] = findViewById(R.id.lay_random_box10); txt_random_box[a] = findViewById(R.id.txt_random_box10);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=11; lay_random_box[a] = findViewById(R.id.lay_random_box11); txt_random_box[a] = findViewById(R.id.txt_random_box11);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=12; lay_random_box[a] = findViewById(R.id.lay_random_box12); txt_random_box[a] = findViewById(R.id.txt_random_box12);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=13; lay_random_box[a] = findViewById(R.id.lay_random_box13); txt_random_box[a] = findViewById(R.id.txt_random_box13);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=14; lay_random_box[a] = findViewById(R.id.lay_random_box14); txt_random_box[a] = findViewById(R.id.txt_random_box14);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=15; lay_random_box[a] = findViewById(R.id.lay_random_box15); txt_random_box[a] = findViewById(R.id.txt_random_box15);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=16; lay_random_box[a] = findViewById(R.id.lay_random_box16); txt_random_box[a] = findViewById(R.id.txt_random_box16);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=17; lay_random_box[a] = findViewById(R.id.lay_random_box17); txt_random_box[a] = findViewById(R.id.txt_random_box17);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=18; lay_random_box[a] = findViewById(R.id.lay_random_box18); txt_random_box[a] = findViewById(R.id.txt_random_box18);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=19; lay_random_box[a] = findViewById(R.id.lay_random_box19); txt_random_box[a] = findViewById(R.id.txt_random_box19);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=20; lay_random_box[a] = findViewById(R.id.lay_random_box20); txt_random_box[a] = findViewById(R.id.txt_random_box20);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);
        a=21; lay_random_box[a] = findViewById(R.id.lay_random_box21); txt_random_box[a] = findViewById(R.id.txt_random_box21);lay_random_box[a].setLayoutParams(lp_lay_random);txt_random_box[a].setTextSize(TypedValue.COMPLEX_UNIT_PX, txt_size);txt_random_box[a].setTypeface(tf);



        LinearLayout lay_words = findViewById(R.id.lay_words);
        LinearLayout.LayoutParams lp_lay_containt_image = (LinearLayout.LayoutParams) lay_words.getLayoutParams();
        lp_lay_containt_image.width = (img_size);
        lp_lay_containt_image.height = (int)(screenHeight*.2);


        lay_words.setLayoutParams(lp_lay_containt_image);








        RelativeLayout.LayoutParams lp_lay_stars = new RelativeLayout.LayoutParams((int)(screenWidth*.32),RelativeLayout.LayoutParams.MATCH_PARENT);
        lay_stars.setLayoutParams(lp_lay_stars);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//        LinearLayout lay_speaker= (LinearLayout) findViewById(R.id.lay_speaker);
//        //LinearLayout.LayoutParams lp_lay_tabBar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)(screenHeight*0.1));
//        LinearLayout.LayoutParams lp_lay_speaker = new LinearLayout.LayoutParams((int)(screenHeight*0.15),(int)(screenHeight*0.042));
//        lay_speaker.setLayoutParams(lp_lay_speaker);
//
//        LinearLayout lay_coin= (LinearLayout) findViewById(R.id.lay_coin);
//        //LinearLayout.LayoutParams lp_lay_tabBar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)(screenHeight*0.1));
//        LinearLayout.LayoutParams lp_lay_coin = new LinearLayout.LayoutParams((int)(screenHeight*0.15),(int)(screenHeight*0.042));
//        lay_coin.setLayoutParams(lp_lay_coin);

        LinearLayout.LayoutParams lp_img_coin1 = new LinearLayout.LayoutParams((int)(screenWidth*.06),(int)(screenHeight*.041));
        ImageView img_coin = (ImageView) findViewById(R.id.img_coin);
        img_coin.setLayoutParams(lp_img_coin1);

        LinearLayout.LayoutParams lp_img_speaker = new LinearLayout.LayoutParams((int)(screenWidth*.062),(int)(screenHeight*.036));
        ImageView img_speaker = (ImageView) findViewById(R.id.img_speaker);
        img_speaker.setLayoutParams(lp_img_speaker);



        TextView txt_coin_count = (TextView) findViewById(R.id.txt_coin_count);
        txt_coin_count.setTypeface(tf);
        txt_coin_count.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.044));





        LinearLayout.LayoutParams lp_img_help = new LinearLayout.LayoutParams((int)(screenWidth*.2),(int)(screenHeight*.085));
        ImageView img_help = findViewById(R.id.img_help);
        img_help.setLayoutParams(lp_img_help);







        ////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        RelativeLayout.LayoutParams lp_lay_help = new RelativeLayout.LayoutParams((int)(screenWidth*.8),(int)(screenHeight*.72));
        RelativeLayout lay_help = findViewById(R.id.lay_help);
        lay_help.setLayoutParams(lp_lay_help);

        RelativeLayout.LayoutParams lp_img_back = new RelativeLayout.LayoutParams((int)(screenWidth*.12),(int)(screenHeight*.07));
        lp_img_back.setMarginStart((int)(screenWidth*.3));
        lp_img_back.topMargin=((int)(screenHeight*.62));
        ImageView img_back  = findViewById(R.id.img_back);
        img_back .setLayoutParams(lp_img_back);

        RelativeLayout.LayoutParams lp_btn_subject1 = new RelativeLayout.LayoutParams((int)(screenWidth*.7),(int)(screenHeight*.1));
        lp_btn_subject1.setMarginStart((int)(screenWidth*.03));
        lp_btn_subject1.topMargin=((int)(screenHeight*.27));
        LinearLayout btn_subject1  = findViewById(R.id.btn_subject1);
        btn_subject1 .setLayoutParams(lp_btn_subject1);

        RelativeLayout.LayoutParams lp_btn_subject2 = new RelativeLayout.LayoutParams((int)(screenWidth*.7),(int)(screenHeight*.1));
        lp_btn_subject2.setMarginStart((int)(screenWidth*.03));
        lp_btn_subject2.topMargin=((int)(screenHeight*.39));
        LinearLayout btn_subject2  = findViewById(R.id.btn_subject2);
        btn_subject2 .setLayoutParams(lp_btn_subject2);

        RelativeLayout.LayoutParams lp_btn_subject3 = new RelativeLayout.LayoutParams((int)(screenWidth*.7),(int)(screenHeight*.1));
        lp_btn_subject3.setMarginStart((int)(screenWidth*.03));
        lp_btn_subject3.topMargin=((int)(screenHeight*.51));
        LinearLayout btn_subject3  = findViewById(R.id.btn_subject3);
        btn_subject3 .setLayoutParams(lp_btn_subject3);

        LinearLayout.LayoutParams lp_img_price1 = new LinearLayout.LayoutParams((int)(screenWidth*.14),(int)(screenHeight*.089));
        ImageView img_price1 = findViewById(R.id.img_price1);
        img_price1.setLayoutParams(lp_img_price1);
        LinearLayout.LayoutParams lp_img_price2 = new LinearLayout.LayoutParams((int)(screenWidth*.14),(int)(screenHeight*.089));
        ImageView img_price2 = findViewById(R.id.img_price2);
        img_price2.setLayoutParams(lp_img_price2);
        LinearLayout.LayoutParams lp_img_price3 = new LinearLayout.LayoutParams((int)(screenWidth*.14),(int)(screenHeight*.089));
        ImageView img_price3 = findViewById(R.id.img_price3);
        img_price3.setLayoutParams(lp_img_price3);



        LinearLayout.LayoutParams lp_lay_btn1 = new LinearLayout.LayoutParams((int)(screenWidth*.23),(int)(screenHeight*.07));
        LinearLayout lay_btn1  = findViewById(R.id.lay_btn1);
        lay_btn1 .setLayoutParams(lp_lay_btn1);
        LinearLayout.LayoutParams lp_lay_btn2 = new LinearLayout.LayoutParams((int)(screenWidth*.23),(int)(screenHeight*.07));
        LinearLayout lay_btn2  = findViewById(R.id.lay_btn2);
        lay_btn2 .setLayoutParams(lp_lay_btn2);
        LinearLayout.LayoutParams lp_lay_btn3 = new LinearLayout.LayoutParams((int)(screenWidth*.23),(int)(screenHeight*.07));
        LinearLayout lay_btn3  = findViewById(R.id.lay_btn3);
        lay_btn3 .setLayoutParams(lp_lay_btn3);

        LinearLayout.LayoutParams lp_img_btn_coin1 = new LinearLayout.LayoutParams((int)(screenWidth*.044),(int)(screenHeight*.044));
        lp_img_btn_coin1.setMarginEnd((int)(screenWidth*.01));
        ImageView img_btn_coin1 = findViewById(R.id.img_btn_coin1);
        img_btn_coin1.setLayoutParams(lp_img_btn_coin1);

        LinearLayout.LayoutParams lp_img_btn_coin2 = new LinearLayout.LayoutParams((int)(screenWidth*.044),(int)(screenHeight*.044));
        lp_img_btn_coin2.setMarginEnd((int)(screenWidth*.01));
        ImageView img_btn_coin2 = findViewById(R.id.img_btn_coin2);
        img_btn_coin2.setLayoutParams(lp_img_btn_coin2);

        LinearLayout.LayoutParams lp_img_btn_coin3 = new LinearLayout.LayoutParams((int)(screenWidth*.044),(int)(screenHeight*.044));
        lp_img_btn_coin3.setMarginEnd((int)(screenWidth*.01));
        ImageView img_btn_coin3 = findViewById(R.id.img_btn_coin3);
        img_btn_coin3.setLayoutParams(lp_img_btn_coin3);

        TextView txt_coint_count1 = (TextView) findViewById(R.id.txt_coint_count1);
        txt_coint_count1.setTypeface(tf);
        txt_coint_count1.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.051));
        TextView txt_coint_count2 = (TextView) findViewById(R.id.txt_coint_count2);
        txt_coint_count2.setTypeface(tf);
        txt_coint_count2.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.051));
        TextView txt_coint_count3 = (TextView) findViewById(R.id.txt_coint_count3);
        txt_coint_count3.setTypeface(tf);
        txt_coint_count3.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.042));


        TextView txt_price1 = (TextView) findViewById(R.id.txt_price1);
        txt_price1.setTypeface(tf);
        txt_price1.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.048));
        LinearLayout.LayoutParams lp_txt_price1 = (LinearLayout.LayoutParams) txt_price1.getLayoutParams();
        lp_txt_price1.topMargin=((int)(screenWidth*.01));
        txt_price1.setLayoutParams(lp_txt_price1);

        TextView txt_price2 = (TextView) findViewById(R.id.txt_price2);
        txt_price2.setTypeface(tf);
        txt_price2.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.048));
        LinearLayout.LayoutParams lp_txt_price2 = (LinearLayout.LayoutParams) txt_price2.getLayoutParams();
        lp_txt_price2.topMargin=((int)(screenWidth*.01));
        txt_price2.setLayoutParams(lp_txt_price2);

        TextView txt_price3 = (TextView) findViewById(R.id.txt_price3);
        txt_price3.setTypeface(tf);
        txt_price3.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.048));
        LinearLayout.LayoutParams lp_txt_price3 = (LinearLayout.LayoutParams) txt_price3.getLayoutParams();
        lp_txt_price3.topMargin=((int)(screenWidth*.01));
        txt_price3.setLayoutParams(lp_txt_price3);

        RelativeLayout.LayoutParams lp_progressBar = new RelativeLayout.LayoutParams((int)(screenWidth*.7),(int)(screenHeight*.1));
        lp_progressBar.setMarginStart((int)(screenWidth*.03));
        lp_progressBar.topMargin=((int)(screenHeight*.05));
        ProgressBar progressBar  = findViewById(R.id.progressBar);
        progressBar .setLayoutParams(lp_progressBar);

        TextView txt_please_wait = (TextView) findViewById(R.id.txt_please_wait);
        txt_please_wait.setTypeface(tf);
        txt_please_wait.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.048));
        RelativeLayout.LayoutParams lp_txt_please_wait = (RelativeLayout.LayoutParams) txt_please_wait.getLayoutParams();
        lp_txt_please_wait.topMargin=((int)(screenWidth*.3));
        lp_txt_please_wait.setMarginStart((int)(screenWidth*.24));

        txt_please_wait.setLayoutParams(lp_txt_please_wait);




    }

    private Rect imageRect;
    private boolean on_touch_disabled =false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if(!on_touch_disabled) {
            imageRect = new Rect();
            int x = (int) event.getX();
            int y = (int) event.getY();

            relativelayout.getGlobalVisibleRect(imageRect);
            if (imageRect.contains(x, y)) {
                int
                        X_image = imageRect.centerX() - (imageRect.width() / 2);
                int
                        Y_image = imageRect.centerY() - (imageRect.height() / 2);

                int
                        new_x = x - X_image;
                int
                        new_y = y - Y_image;
                int cirle_size = (int) (img_size / 64);


                int
                        circle_number = ((int) Math.floor(new_x / cirle_size));
                int
                        circle_number1 = ((int) Math.floor(new_y / cirle_size));

                //   circle_number=63-circle_number+1;
                //   circle_number1=63-circle_number1+1;

                for (int l = 7; l >= 2; l--) {
                    int
                            i = (circle_number / ((int) ((Math.pow(2, l - 1)))));
                    int
                            j = (circle_number1 / ((int) ((Math.pow(2, l - 1)))));

                    i = i * (int) (Math.pow(2, l - 1)) + 1;
                    j = j * (int) (Math.pow(2, l - 1)) + 1;
                    if (i == 0)
                        i = 1;
                    if (j == 0)
                        j = 1;
                    if (i < 65 && j < 65)
                        if (img_circles[l][i][j].getVisibility() != View.GONE && stars_size > 0) {
                            stars_size -= dec_count;
                            RelativeLayout.LayoutParams lp_lay_stars = new RelativeLayout.LayoutParams((int) stars_size, RelativeLayout.LayoutParams.MATCH_PARENT);
                            lay_stars.setLayoutParams(lp_lay_stars);
                            img_circles[l][i][j].setVisibility(View.GONE);
                            img_circles[l - 1][i][j].setVisibility(View.VISIBLE);
                            img_circles[l - 1][i][j + ((int) ((Math.pow(2, l - 2))))].setVisibility(View.VISIBLE);
                            img_circles[l - 1][i + ((int) ((Math.pow(2, l - 2))))][j + ((int) ((Math.pow(2, l - 2))))].setVisibility(View.VISIBLE);
                            img_circles[l - 1][i + ((int) ((Math.pow(2, l - 2))))][j].setVisibility(View.VISIBLE);

                            break;
                        }
                    // Log.d("majid", "x= " + String.valueOf(i) + "  y= " + String.valueOf(j)+" l= "+String.valueOf(l));


                }
                //Log.d("majid","x= "+String.valueOf(x)+"  y= "+String.valueOf(y));
            }


//        for(int i=2;i<=7;i++)
//        {
//
//        }


        }

        return false;
    }

//    public void break_image(ImageView img_view) {
//        //Toast.makeText(this, String.valueOf(img_view.getWidth()), Toast.LENGTH_SHORT).show();
//        //if (img_view.getTextDirection() != 2)
//        {
//
//            //if (img_view.getWidth() > 20)
//            {
//                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) img_view.getLayoutParams();
//                // Log.d("majid", "touch passing over imageView" + String.valueOf(lp.getMarginStart()));
//                int
//                        circle_size = img_view.getWidth();
//                try {
//                    bitmap = Bitmap.createBitmap(
//                            circle_size / 2, // Width
//                            circle_size / 2, // Height
//                            Bitmap.Config.ARGB_8888 // Config
//                    );
//
//                    // Initialize a new Canvas instance
//                    Canvas canvas = new Canvas(bitmap);
//
//                    // Draw a solid color to the canvas background
//                    //  canvas.drawColor(Color.WHITE);
//
//                    // Initialize a new Paint instance to draw the Circle
//                    Paint paint = new Paint();
//                    paint.setStyle(Paint.Style.FILL);
//
//                    int clr = get_color(lp.getMarginStart(), lp.topMargin, circle_size / 2);
//
//                    paint.setColor(clr);
//                    paint.setAntiAlias(true);
//
//
//                    canvas.drawCircle(
//                            circle_size / 4, // cx
//                            circle_size / 4, // cy
//                            (int) (circle_size / 4), // Radius
//                            paint // Paint
//                    );
//                    for(int i=1;i<img_count-1;i++)
//                    {
//                        if(img_circles[i].getTextDirection()==2)
//                        {
//                          //  img_circles[i]=null;
//
//                        }
//                    }
//                    System.gc();
//                    if(clr!=-1) {
//                        img_count++;
//                        img_circles[img_count] = new ImageView(GameBoard.this);
//                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
//                        params.setMarginStart(lp.getMarginStart());
//                        params.topMargin = lp.topMargin;
//                        img_circles[img_count].setLayoutParams(params);
//                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
//                        relativelayout.addView(img_circles[img_count]);
//
//                        img_circles[img_count].setImageBitmap(bitmap);
//
//                    }
//
//                    img_view.setTextDirection(2);
//                //    img_view.setVisibility(View.GONE);
//                    //  img_circles[Integer.valueOf(img_view.getContentDescription().toString())]=null;
//
//                } catch (Exception e2) {
//
//                }
//
//
//                try {
//                    bitmap = Bitmap.createBitmap(
//                            circle_size / 2, // Width
//                            circle_size / 2, // Height
//                            Bitmap.Config.ARGB_8888 // Config
//                    );
//
//                    // Initialize a new Canvas instance
//                    Canvas canvas = new Canvas(bitmap);
//
//                    // Draw a solid color to the canvas background
//                    canvas.drawColor(Color.WHITE);
//
//                    // Initialize a new Paint instance to draw the Circle
//                    Paint paint = new Paint();
//                    paint.setStyle(Paint.Style.FILL);
//                    int clr = get_color(lp.getMarginStart() + (circle_size / 2), lp.topMargin, circle_size / 2);
//
//                    paint.setColor(clr);
//
//                    paint.setAntiAlias(true);
//
//
//                    canvas.drawCircle(
//                            circle_size / 4, // cx
//                            circle_size / 4, // cy
//                            (int) (circle_size / 4), // Radius
//                            paint // Paint
//                    );
//                    if(clr!=-1) {
//                        img_count++;
//                        img_circles[img_count] = new ImageView(GameBoard.this);
//                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
//                        params.setMarginStart(lp.getMarginStart() + (circle_size / 2));
//                        params.topMargin = lp.topMargin;
//                        img_circles[img_count].setLayoutParams(params);
//                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
//                        relativelayout.addView(img_circles[img_count]);
//
//                        img_circles[img_count].setImageBitmap(bitmap);
//                    }
//                } catch (Exception e3) {
//
//                }
//                try {
//                    bitmap = Bitmap.createBitmap(
//                            circle_size / 2, // Width
//                            circle_size / 2, // Height
//                            Bitmap.Config.ARGB_8888 // Config
//                    );
//
//                    // Initialize a new Canvas instance
//                    Canvas canvas = new Canvas(bitmap);
//
//                    // Draw a solid color to the canvas background
//                    canvas.drawColor(Color.WHITE);
//
//                    // Initialize a new Paint instance to draw the Circle
//                    Paint paint = new Paint();
//                    paint.setStyle(Paint.Style.FILL);
//                    int clr = get_color(lp.getMarginStart(), lp.topMargin + (circle_size / 2), circle_size / 2);
//
//                    paint.setColor(clr);
//
//                    paint.setAntiAlias(true);
//
//
//                    canvas.drawCircle(
//                            circle_size / 4, // cx
//                            circle_size / 4, // cy
//                            (int) (circle_size / 4), // Radius
//                            paint // Paint
//                    );
//                    if(clr!=-1) {
//                        img_count++;
//                        img_circles[img_count] = new ImageView(GameBoard.this);
//                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
//                        params.setMarginStart(lp.getMarginStart());
//                        params.topMargin = lp.topMargin + (circle_size / 2);
//                        img_circles[img_count].setLayoutParams(params);
//                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
//                        relativelayout.addView(img_circles[img_count]);
//
//                        img_circles[img_count].setImageBitmap(bitmap);
//                    }
//                } catch (Exception e3) {
//
//                }
//
//                try {
//                    bitmap = Bitmap.createBitmap(
//                            circle_size / 2, // Width
//                            circle_size / 2, // Height
//                            Bitmap.Config.ARGB_8888 // Config
//                    );
//
//                    // Initialize a new Canvas instance
//                    Canvas canvas = new Canvas(bitmap);
//
//                    // Draw a solid color to the canvas background
//                    canvas.drawColor(Color.WHITE);
//
//                    // Initialize a new Paint instance to draw the Circle
//                    Paint paint = new Paint();
//                    paint.setStyle(Paint.Style.FILL);
//                    int clr = get_color(lp.getMarginStart() + (circle_size / 2), lp.topMargin + (circle_size / 2), circle_size / 2);
//
//                    paint.setColor(clr);
//
//                    paint.setAntiAlias(true);
//
//
//                    canvas.drawCircle(
//                            circle_size / 4, // cx
//                            circle_size / 4, // cy
//                            (int) (circle_size / 4), // Radius
//                            paint // Paint
//                    );
//                    if(clr!=-1) {
//                        img_count++;
//                        img_circles[img_count] = new ImageView(GameBoard.this);
//                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
//                        params.setMarginStart(lp.getMarginStart() + (circle_size / 2));
//                        params.topMargin = lp.topMargin + (circle_size / 2);
//                        img_circles[img_count].setLayoutParams(params);
//                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
//                        relativelayout.addView(img_circles[img_count]);
//
//                        img_circles[img_count].setImageBitmap(bitmap);
//                    }
//                } catch (Exception e3) {
//
//                }
//
//
//                //  Toast.makeText(this, String.valueOf(img_view.getContentDescription().toString()), Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//    }


    private int get_color(int start, int top, int size) {


        //  Toast.makeText(this, "Start"+String.valueOf(start)+" top="+String.valueOf(top)+" size="+String.valueOf(size), Toast.LENGTH_SHORT).show();
        Bitmap bitmap = ((BitmapDrawable) imageview1.getDrawable()).getBitmap();

        int
                sum_red = 0;
        int
                sum_blue = 0;
        int
                sum_green = 0;
        int
                pixel_count = 0;
        int redValue = 0;
        int blueValue = 0;
        int greenValue = 0;
        int
                jump_size = 12;
        if (size < 100)
            jump_size = 6;
        for (int i = start; i < start + size; i += jump_size) {
            for (int j = top; j < top + size; j += jump_size) {


                Matrix inverse = new Matrix();
                imageview1.getImageMatrix().invert(inverse);
                float[] touchPoint = new float[]{i, j};
                inverse.mapPoints(touchPoint);
                int xCoord = Integer.valueOf((int) touchPoint[0]);
                int yCoord = Integer.valueOf((int) touchPoint[1]);
                if(xCoord<bitmap.getWidth() && yCoord<bitmap.getHeight()) {
                    int pixel = bitmap.getPixel(xCoord, yCoord);

                pixel_count++;
                redValue = Color.red(pixel);
                blueValue = Color.blue(pixel);
                greenValue = Color.green(pixel);
                sum_red += redValue;
                sum_green += greenValue;
                sum_blue += blueValue;
                }
            }

        }
        int avg_red = (int) (sum_red / pixel_count);
        int avg_blue = (int) (sum_blue / pixel_count);
        int avg_green = (int) (sum_green / pixel_count);


        return Color.rgb(avg_red, avg_green, avg_blue);
    }

    Bitmap bitmap;

    public void clk_btn(View view) {




    }


    public void clk_btn1(View view) {

    }

    public void clk_random_txt(View view) {

        //http://193.104.22.14:2055/CPSMSService/Access
        TextView txt_random = (TextView) view;
//        int[] ans_box = new int[17];
//        ans_box[1]= R.id.txt_ans_box1;
//        ans_box[2]= R.id.txt_ans_box2;
//        ans_box[3]= R.id.txt_ans_box3;
//        ans_box[4]= R.id.txt_ans_box4;
//        ans_box[5]= R.id.txt_ans_box5;
//        ans_box[6]= R.id.txt_ans_box6;
//        ans_box[7]= R.id.txt_ans_box7;
//        ans_box[8]= R.id.txt_ans_box8;
//        ans_box[9]= R.id.txt_ans_box9;
//        ans_box[10]= R.id.txt_ans_box10;
//        ans_box[11]= R.id.txt_ans_box11;
//        ans_box[12]= R.id.txt_ans_box12;
//        ans_box[13]= R.id.txt_ans_box13;
//        ans_box[14]= R.id.txt_ans_box14;
//        ans_box[15]= R.id.txt_ans_box15;
//        ans_box[16]= R.id.txt_ans_box16;


        int[] random_box = new int[22];
        random_box[1]= R.id.txt_random_box1;
        random_box[2]= R.id.txt_random_box2;
        random_box[3]= R.id.txt_random_box3;
        random_box[4]= R.id.txt_random_box4;
        random_box[5]= R.id.txt_random_box5;
        random_box[6]= R.id.txt_random_box6;
        random_box[7]= R.id.txt_random_box7;
        random_box[8]= R.id.txt_random_box8;
        random_box[9]= R.id.txt_random_box9;
        random_box[10]= R.id.txt_random_box10;
        random_box[11]= R.id.txt_random_box11;
        random_box[12]= R.id.txt_random_box12;
        random_box[13]= R.id.txt_random_box13;
        random_box[14]= R.id.txt_random_box14;
        random_box[15]= R.id.txt_random_box15;
        random_box[16]= R.id.txt_random_box16;
        random_box[17]= R.id.txt_random_box17;
        random_box[18]= R.id.txt_random_box18;
        random_box[19]= R.id.txt_random_box19;
        random_box[20]= R.id.txt_random_box20;
        random_box[21]= R.id.txt_random_box21;
        int
            selected_text_box=0,index=0;
        for(int i=1;i<=21;i++)
        {
            if(random_box[i]==view.getId()  )
            {
                selected_text_box=random_box[i];
                index=i;
                break;
            }
        }
        TextView txt_index = findViewById(selected_text_box);
        if(txt_index.getText().toString().length()>0)
        for(int i=1;i<=16;i++)
        {
            if(ans_box[i]==0 )
            {
                if(i<=target_word.length()) {
                    txt_ans_box[i].setText(txt_index.getText().toString());
                    txt_index.setText("");
                    ans_box[i] = index;
                    break;
                }
                else
                {

                    break;
                }
            }
        }

     //   Toast.makeText(this, txt_index.getText().toString(), Toast.LENGTH_SHORT).show();


        String
                ans_str="";
        for(int i=0;i<target_word.length();i++)
        {
            ans_str+=(txt_ans_box[i+1].getText().toString());

        }
        //Toast.makeText(this, ans_str, Toast.LENGTH_SHORT).show();
        if(ans_str.equals(target_word))
        {
            Toast.makeText(this, "Its ok", Toast.LENGTH_SHORT).show();
        }

    }

    public void clk_ans_txt(View view) {
        int[] ans_box1 = new int[17];
        ans_box1[1]= R.id.txt_ans_box1;
        ans_box1[2]= R.id.txt_ans_box2;
        ans_box1[3]= R.id.txt_ans_box3;
        ans_box1[4]= R.id.txt_ans_box4;
        ans_box1[5]= R.id.txt_ans_box5;
        ans_box1[6]= R.id.txt_ans_box6;
        ans_box1[7]= R.id.txt_ans_box7;
        ans_box1[8]= R.id.txt_ans_box8;
        ans_box1[9]= R.id.txt_ans_box9;
        ans_box1[10]= R.id.txt_ans_box10;
        ans_box1[11]= R.id.txt_ans_box11;
        ans_box1[12]= R.id.txt_ans_box12;
        ans_box1[13]= R.id.txt_ans_box13;
        ans_box1[14]= R.id.txt_ans_box14;
        ans_box1[15]= R.id.txt_ans_box15;
        ans_box1[16]= R.id.txt_ans_box16;



        int
                selected_text_box=0,index=0;
        for(int i=1;i<=16;i++)
        {
            if(ans_box1[i]==view.getId() && ans_box_status[i]==0 )
            {
                selected_text_box=ans_box1[i];
                index=i;
                TextView txt_index = findViewById(selected_text_box);
                if(txt_index.getText().toString().length()>0)
                {
                    txt_random_box[ans_box[index]].setText(txt_index.getText());
                    txt_index.setText("");
                    ans_box[index]=0;
                    ans_box_status[index]=0;
                }
                break;
            }
        }




    }
        int
            pr=1;
    public void clk_mute(View view) {
//        tim = new Timer("break");
//        tim.start();




    }

    public void clk_coin(View view) {

        Log.d("majid","bb");



        Log.d("majid","aa");
    }

    private void draw_circle(int p,int level) {
    //    Bitmap bitmap = ((BitmapDrawable) imageview1.getDrawable()).getBitmap();
        int cirle_size=(int)(img_size/64);
        Paint paint = new Paint();
        int clr1=1;
        int clr;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int
                x_color =1;
        int
                y_color =1;
        for(int i=0;i<64 ;i+=p)
            for(int j=0;j<64;j+=p)
            {

                if(i==0)
                    x_color=1;
                else
                    x_color = ((i)*cirle_size);

                if(j==0)
                    y_color=1;
                else
                    y_color = ((j)*cirle_size);

                clr = get_color(x_color,y_color , cirle_size*p);
                img_color_circle[img_count]=clr;

                bitmap = Bitmap.createBitmap(

                        cirle_size*p, // Width
                        cirle_size*p, // Height
                        Bitmap.Config.ARGB_8888 // Config
                );

                // Initialize a new Canvas instance
                Canvas canvas = new Canvas(bitmap);

                // Draw a solid color to the canvas background
                canvas.drawColor(Color.WHITE);

                // Initialize a new Paint instance to draw the Circle
                //   if(clr!=clr1)
                {
                    paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(clr);
                    paint.setAntiAlias(true);

                }


                canvas.drawCircle(
                        (cirle_size/2)*p, // cx
                        (cirle_size/2)*p, // cy
                        (int) ((cirle_size / 2)*p), // Radius
                        paint // Paint
                );

                // Display the newly created bitmap on app interface
                // ImageView mImageView = findViewById(R.id.imageView);



                img_circles[level][(i+1)][(j+1)] = new ImageView(GameBoard.this);

                params.setMarginStart(x_color);
                params.topMargin = y_color;
                img_circles[level][(i+1)][(j+1)].setLayoutParams(params);
                //img_circles[img_count].setContentDescription(String.valueOf(img_count));
                relativelayout.addView(img_circles[level][(i+1)][(j+1)]);

                img_circles[level][(i+1)][(j+1)].setImageBitmap(bitmap);
                String
                        str1="i="+String.valueOf(i+1)+"j="+String.valueOf(j+1)+"l="+String.valueOf(level);
                img_circles[level][(i+1)][(j+1)].setContentDescription(str1);
                if(p!=64)
                    img_circles[level][(i+1)][(j+1)].setVisibility(View.GONE);
//                onTouchView[img_count] = img_circles[level][(i+1)][(j+1)];
//                onTouchView[img_count].setOnTouchListener(this);
                img_count++;



            }
    }
//    public boolean onTouch(View view, MotionEvent event)
//    {
//       // Log.d("majid",);
//        String
//                desc=view.getContentDescription().toString();
//       // if(event.getAction() == 0)
//        {
//            int start1 = desc.indexOf("i=");
//            int end1 = desc.indexOf("j=");
//            int  ii = Integer.valueOf(desc.substring(start1 + 2, end1));
//           // Log.d("majid",ii);
//
//             start1 = desc.indexOf("j=");
//             end1 = desc.indexOf("l=");
//            int  jj = Integer.valueOf(desc.substring(start1 + 2, end1));
//            //Log.d("majid",jj);
//
//             start1 = desc.indexOf("l=");
//
//            int  ll = Integer.valueOf(desc.substring(start1 + 2, desc.length()));
//           // Log.d("majid",ll);
//
//            view.setVisibility(View.GONE);
//            img_circles[ll-1][ii][jj].setVisibility(View.VISIBLE);
//            img_circles[ll-1][ii][jj+((int)((Math.pow(2,ll-2))))].setVisibility(View.VISIBLE);
//            img_circles[ll-1][ii+((int)((Math.pow(2,ll-2))))][jj+((int)((Math.pow(2,ll-2))))].setVisibility(View.VISIBLE);
//            img_circles[ll-1][ii+((int)((Math.pow(2,ll-2))))][jj].setVisibility(View.VISIBLE);
//            //  Code to display x and y go here
//        }
//        return false;
//    }

    int
        ii=1,jj=1,ll=7;

    public void clk_help(View view) {



        RelativeLayout lay_help_main = findViewById(R.id.lay_help_main);
        lay_help_main.setVisibility(View.VISIBLE);

        LinearLayout lay_main = findViewById(R.id.lay_main);
        fun.enableDisableView(lay_main,false);
        on_touch_disabled=true;

    }

    public void clk_back_from_help(View view) {
        RelativeLayout lay_help_main = findViewById(R.id.lay_help_main);
        lay_help_main.setVisibility(View.GONE);

        LinearLayout lay_main = findViewById(R.id.lay_main);
        fun.enableDisableView(lay_main,true);
        on_touch_disabled=false;
    }

    public void clk_help1(View view) {
      //  Toast.makeText(this, String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();
        if(view.getId()==R.id.btn_help1)
        {
            int[] ans_box1 = new int[17];
            ans_box1[1]= R.id.txt_ans_box1;
            ans_box1[2]= R.id.txt_ans_box2;
            ans_box1[3]= R.id.txt_ans_box3;
            ans_box1[4]= R.id.txt_ans_box4;
            ans_box1[5]= R.id.txt_ans_box5;
            ans_box1[6]= R.id.txt_ans_box6;
            ans_box1[7]= R.id.txt_ans_box7;
            ans_box1[8]= R.id.txt_ans_box8;
            ans_box1[9]= R.id.txt_ans_box9;
            ans_box1[10]= R.id.txt_ans_box10;
            ans_box1[11]= R.id.txt_ans_box11;
            ans_box1[12]= R.id.txt_ans_box12;
            ans_box1[13]= R.id.txt_ans_box13;
            ans_box1[14]= R.id.txt_ans_box14;
            ans_box1[15]= R.id.txt_ans_box15;
            ans_box1[16]= R.id.txt_ans_box16;

            boolean flag=true;
            int rnd =0;
            while (flag)
            {
                flag=false;
                rnd = new Random().nextInt(target_word.length())+1;

                if(ans_box_status[rnd]==1)
                {
                    flag=true;
                }
            }
           // Toast.makeText(this, String.valueOf(rnd), Toast.LENGTH_SHORT).show();
            TextView txt_index = findViewById(ans_box1[rnd]);
            clk_ans_txt(txt_index);
            ans_box[rnd] = -1;
            ans_box_status[rnd]=1;
            txt_ans_box[rnd].setText(String.valueOf(target_word.toUpperCase().charAt(rnd-1)));
            txt_ans_box[rnd].setTextColor(Color.rgb(100,100,100));


            clk_back_from_help(view);
            String
                    ans_str="";
            for(int i=0;i<target_word.length();i++)
            {
                ans_str+=(txt_ans_box[i+1].getText().toString());

            }
            //Toast.makeText(this, ans_str, Toast.LENGTH_SHORT).show();
            if(ans_str.equals(target_word))
            {
                Toast.makeText(this, "Its ok", Toast.LENGTH_SHORT).show();
            }
        }

        if(view.getId()==R.id.btn_help2)
        {
            LinearLayout lay_btn2_container = findViewById(R.id.lay_btn2_container);
            lay_btn2_container.setVisibility(View.GONE);
            LinearLayout lay_contain_text2 = findViewById(R.id.lay_contain_text2);
            LinearLayout.LayoutParams lp_lay_contain_text2 = (LinearLayout.LayoutParams) lay_contain_text2.getLayoutParams();
            lp_lay_contain_text2.weight = 6;
            lay_contain_text2.setLayoutParams(lp_lay_contain_text2);

            TextView txt_coint_count2=findViewById(R.id.txt_coint_count2);
            txt_coint_count2.setText(tip_of_target_word);
        }
        if(view.getId()==R.id.btn_help3)
        {
             tim = new Timer("break");
             tim.start();
             on_touch_disabled=true;
             clk_back_from_help(view);
        }
    }

    int drawable = 0;
    int drawable_count = 0;

    private ProgressDialog pDialog;
    public class Timer extends Thread {

        int oneSecond=1000;
        int value=0;
        String TAG="Timer";
        String typ="";
        public long milles=10;

        //@Override
        public Timer(String type)
        {
            typ = type;
        }
        @Override
        public void run() {

            for(;;){


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {



                        if(typ.equals("draw"))
                        {
                            if( drawable_count==1) {
                                drawable=1;
                                draw_circle(1, 1);
                                draw_circle(2, 2);

                                draw_circle(4, 3);
                                draw_circle(8, 4);
                                draw_circle(16, 5);
                                draw_circle(32, 6);
                                draw_circle(64, 7);

                                LinearLayout lay_main = findViewById(R.id.lay_main);
                                fun.enableDisableView(lay_main,true);
                                on_touch_disabled=false;
                                RelativeLayout lay_wait = findViewById(R.id.lay_wait);
                                lay_wait.setVisibility(View.GONE);
                             //   Toast.makeText(GameBoard.this, "aaaaa", Toast.LENGTH_SHORT).show();
                            }
                            drawable_count++;

                        }

                        if(typ.equals("break")) {
                            Log.d("majid","aloooo");
                            if(ll>1) {
                                img_circles[ll][ii][jj].setVisibility(View.GONE);
                                img_circles[ll-1][ii][jj].setVisibility(View.VISIBLE);
                                img_circles[ll-1][ii][jj+((int)((Math.pow(2,ll-2))))].setVisibility(View.VISIBLE);
                                img_circles[ll-1][ii+((int)((Math.pow(2,ll-2))))][jj+((int)((Math.pow(2,ll-2))))].setVisibility(View.VISIBLE);
                                img_circles[ll-1][ii+((int)((Math.pow(2,ll-2))))][jj].setVisibility(View.VISIBLE);

                                jj += (((int) ((Math.pow(2, ll - 1)))));
                                if (jj > 64) {
                                    ii += ((int) ((Math.pow(2, ll - 1))));
                                    jj = 1;
                                }
                                if (ii > 64) {
                                    ll--;
                                    ii = 1;
                                }
                            }








                            }









                    }
                });


                //   Log.d("majid", String.valueOf(value));
                //Thread.currentThread();
                try {


                    Thread.sleep(milles);
                    //	Log.d(TAG, " " + value);
                } catch (InterruptedException e) {
                    System.out.println("timer interrupted");
                    //value=0;
                    e.printStackTrace();
                }
            }
        }



    }

}
