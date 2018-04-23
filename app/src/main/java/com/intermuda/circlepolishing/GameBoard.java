package com.intermuda.circlepolishing;

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
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameBoard extends AppCompatActivity {
    ImageView imageview;
    ImageView imageview1;
    private int screenWidth;
    private int screenHeight;
    String
            font_name = "";
    functions fun;

    Typeface tf;
    int img_count = 1;

    ImageView[] img_circles = new ImageView[500000];
    int
            img_size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        // Circle circle = (Circle) findViewById(R.id.circle);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        fun = new functions();

        font_name = fun.font_name;


//        SharedPreferences settings = getApplicationContext().getSharedPreferences("homeScore", 0);
//        fun.u_name =  settings.getString("homeScore","");
//        functions.u_name =  settings.getString("homeScore","");

        tf = Typeface.createFromAsset(getAssets(),font_name );
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        imageview1 = findViewById(R.id.imageView);


        img_circles[img_count] = new ImageView(GameBoard.this);
        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        img_circles[img_count].setContentDescription(String.valueOf(img_count));
        img_circles[img_count].setLayoutParams(params);
        relativelayout.addView(img_circles[img_count]);


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
        for (int i = 1; i < bitmap.getWidth(); i += 4) {
            for (int j = 1; j < bitmap.getHeight(); j += 4) {

                int pixel = bitmap.getPixel(i, j);

                pixel_count++;
                redValue = Color.red(pixel);
                blueValue = Color.blue(pixel);
                greenValue = Color.green(pixel);
                sum_red += redValue;
                sum_green += greenValue;
                sum_blue += blueValue;

            }

        }
        int avg_red = (int) (sum_red / pixel_count);
        int avg_blue = (int) (sum_blue / pixel_count);
        int avg_green = (int) (sum_green / pixel_count);

        bitmap = Bitmap.createBitmap(
                img_size, // Width
                img_size, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);

        // Draw a solid color to the canvas background
        canvas.drawColor(Color.WHITE);

        // Initialize a new Paint instance to draw the Circle
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(avg_red, avg_green, avg_blue));
        paint.setAntiAlias(true);


        canvas.drawCircle(
                canvas.getWidth() / 2, // cx
                canvas.getHeight() / 2, // cy
                (int) (img_size / 2), // Radius
                paint // Paint
        );

        // Display the newly created bitmap on app interface
        // ImageView mImageView = findViewById(R.id.imageView);
        img_circles[img_count].setImageBitmap(bitmap);
        set_content_size();


    }
    private void set_content_size()
    {


        LinearLayout lay_words = findViewById(R.id.lay_words);
        LinearLayout.LayoutParams lp_lay_containt_image = (LinearLayout.LayoutParams) lay_words.getLayoutParams();
        lp_lay_containt_image.width = (img_size);
        lp_lay_containt_image.height = (int)(screenHeight*.2);
        lp_lay_containt_image.setMarginStart((int) (screenWidth * .13));
        lp_lay_containt_image.topMargin = (int) (screenWidth * .001);


        lay_words.setLayoutParams(lp_lay_containt_image);





        TextView txt_subject= (TextView) findViewById(R.id.txt_subject);
        txt_subject.setTypeface(tf);
        txt_subject.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.039));





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

        LinearLayout.LayoutParams lp_img_coin1 = new LinearLayout.LayoutParams((int)(screenWidth*.078),(int)(screenHeight*.054));
        ImageView img_coin = (ImageView) findViewById(R.id.img_coin);
        img_coin.setLayoutParams(lp_img_coin1);

        LinearLayout.LayoutParams lp_img_speaker = new LinearLayout.LayoutParams((int)(screenWidth*.087),(int)(screenHeight*.051));
        ImageView img_speaker = (ImageView) findViewById(R.id.img_speaker);
        img_speaker.setLayoutParams(lp_img_speaker);



        TextView txt_coin_count = (TextView) findViewById(R.id.txt_coin_count);
        txt_coin_count.setTypeface(tf);
        txt_coin_count.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (screenWidth * 0.052));











    }

    private Rect imageRect;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);


        imageRect = new Rect();
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (int i = 1; i <= img_count; i++) {
            img_circles[i].getGlobalVisibleRect(imageRect);

            if (imageRect.contains(x, y)) {
                break_image(img_circles[i]);
                // break;
            }
        }

        return false;
    }

    public void break_image(ImageView img_view) {
        //Toast.makeText(this, String.valueOf(img_view.getWidth()), Toast.LENGTH_SHORT).show();
        if (img_view.getTextDirection() != 2) {

            if (img_view.getWidth() > 20) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) img_view.getLayoutParams();
                // Log.d("majid", "touch passing over imageView" + String.valueOf(lp.getMarginStart()));
                int
                        circle_size = img_view.getWidth();
                try {
                    bitmap = Bitmap.createBitmap(
                            circle_size / 2, // Width
                            circle_size / 2, // Height
                            Bitmap.Config.ARGB_8888 // Config
                    );

                    // Initialize a new Canvas instance
                    Canvas canvas = new Canvas(bitmap);

                    // Draw a solid color to the canvas background
                    //  canvas.drawColor(Color.WHITE);

                    // Initialize a new Paint instance to draw the Circle
                    Paint paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);

                    int clr = get_color(lp.getMarginStart(), lp.topMargin, circle_size / 2);

                    paint.setColor(clr);
                    paint.setAntiAlias(true);


                    canvas.drawCircle(
                            circle_size / 4, // cx
                            circle_size / 4, // cy
                            (int) (circle_size / 4), // Radius
                            paint // Paint
                    );

                    if(clr!=-1) {
                        img_count++;
                        img_circles[img_count] = new ImageView(GameBoard.this);
                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
                        params.setMarginStart(lp.getMarginStart());
                        params.topMargin = lp.topMargin;
                        img_circles[img_count].setLayoutParams(params);
                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
                        relativelayout.addView(img_circles[img_count]);

                        img_circles[img_count].setImageBitmap(bitmap);

                    }

                    img_view.setTextDirection(2);
                    img_view.setVisibility(View.GONE);
                    //  img_circles[Integer.valueOf(img_view.getContentDescription().toString())]=null;

                } catch (Exception e2) {

                }


                try {
                    bitmap = Bitmap.createBitmap(
                            circle_size / 2, // Width
                            circle_size / 2, // Height
                            Bitmap.Config.ARGB_8888 // Config
                    );

                    // Initialize a new Canvas instance
                    Canvas canvas = new Canvas(bitmap);

                    // Draw a solid color to the canvas background
                    canvas.drawColor(Color.WHITE);

                    // Initialize a new Paint instance to draw the Circle
                    Paint paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);
                    int clr = get_color(lp.getMarginStart() + (circle_size / 2), lp.topMargin, circle_size / 2);

                    paint.setColor(clr);

                    paint.setAntiAlias(true);


                    canvas.drawCircle(
                            circle_size / 4, // cx
                            circle_size / 4, // cy
                            (int) (circle_size / 4), // Radius
                            paint // Paint
                    );
                    if(clr!=-1) {
                        img_count++;
                        img_circles[img_count] = new ImageView(GameBoard.this);
                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
                        params.setMarginStart(lp.getMarginStart() + (circle_size / 2));
                        params.topMargin = lp.topMargin;
                        img_circles[img_count].setLayoutParams(params);
                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
                        relativelayout.addView(img_circles[img_count]);

                        img_circles[img_count].setImageBitmap(bitmap);
                    }
                } catch (Exception e3) {

                }
                try {
                    bitmap = Bitmap.createBitmap(
                            circle_size / 2, // Width
                            circle_size / 2, // Height
                            Bitmap.Config.ARGB_8888 // Config
                    );

                    // Initialize a new Canvas instance
                    Canvas canvas = new Canvas(bitmap);

                    // Draw a solid color to the canvas background
                    canvas.drawColor(Color.WHITE);

                    // Initialize a new Paint instance to draw the Circle
                    Paint paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);
                    int clr = get_color(lp.getMarginStart(), lp.topMargin + (circle_size / 2), circle_size / 2);

                    paint.setColor(clr);

                    paint.setAntiAlias(true);


                    canvas.drawCircle(
                            circle_size / 4, // cx
                            circle_size / 4, // cy
                            (int) (circle_size / 4), // Radius
                            paint // Paint
                    );
                    if(clr!=-1) {
                        img_count++;
                        img_circles[img_count] = new ImageView(GameBoard.this);
                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
                        params.setMarginStart(lp.getMarginStart());
                        params.topMargin = lp.topMargin + (circle_size / 2);
                        img_circles[img_count].setLayoutParams(params);
                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
                        relativelayout.addView(img_circles[img_count]);

                        img_circles[img_count].setImageBitmap(bitmap);
                    }
                } catch (Exception e3) {

                }

                try {
                    bitmap = Bitmap.createBitmap(
                            circle_size / 2, // Width
                            circle_size / 2, // Height
                            Bitmap.Config.ARGB_8888 // Config
                    );

                    // Initialize a new Canvas instance
                    Canvas canvas = new Canvas(bitmap);

                    // Draw a solid color to the canvas background
                    canvas.drawColor(Color.WHITE);

                    // Initialize a new Paint instance to draw the Circle
                    Paint paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);
                    int clr = get_color(lp.getMarginStart() + (circle_size / 2), lp.topMargin + (circle_size / 2), circle_size / 2);

                    paint.setColor(clr);

                    paint.setAntiAlias(true);


                    canvas.drawCircle(
                            circle_size / 4, // cx
                            circle_size / 4, // cy
                            (int) (circle_size / 4), // Radius
                            paint // Paint
                    );
                    if(clr!=-1) {
                        img_count++;
                        img_circles[img_count] = new ImageView(GameBoard.this);
                        RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.lay_contain_image);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circle_size / 2, circle_size / 2);
                        params.setMarginStart(lp.getMarginStart() + (circle_size / 2));
                        params.topMargin = lp.topMargin + (circle_size / 2);
                        img_circles[img_count].setLayoutParams(params);
                        img_circles[img_count].setContentDescription(String.valueOf(img_count));
                        relativelayout.addView(img_circles[img_count]);

                        img_circles[img_count].setImageBitmap(bitmap);
                    }
                } catch (Exception e3) {

                }


                //  Toast.makeText(this, String.valueOf(img_view.getContentDescription().toString()), Toast.LENGTH_SHORT).show();

            }
        }

    }


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
        int avg_red = (int) (sum_red / pixel_count);
        int avg_blue = (int) (sum_blue / pixel_count);
        int avg_green = (int) (sum_green / pixel_count);


        return Color.rgb(avg_red, avg_green, avg_blue);
    }

    Bitmap bitmap;

    public void clk_btn(View view) {




    }


    public void clk_btn1(View view) {
        //  ImageView imageView = findViewById(R.id.imageView);
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
        for (int i = 1; i < bitmap.getWidth(); i += 4) {
            for (int j = 1; j < bitmap.getHeight(); j += 4) {

                int pixel = bitmap.getPixel(i, j);

                pixel_count++;
                redValue = Color.red(pixel);
                blueValue = Color.blue(pixel);
                greenValue = Color.green(pixel);
                sum_red += redValue;
                sum_green += greenValue;
                sum_blue += blueValue;

            }

        }
        int avg_red = (int) (sum_red / pixel_count);
        int avg_blue = (int) (sum_blue / pixel_count);
        int avg_green = (int) (sum_green / pixel_count);

        bitmap = Bitmap.createBitmap(
                img_size, // Width
                img_size, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);

        // Draw a solid color to the canvas background
        canvas.drawColor(Color.WHITE);

        // Initialize a new Paint instance to draw the Circle
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(avg_red, avg_green, avg_blue));
        paint.setAntiAlias(true);


        canvas.drawCircle(
                canvas.getWidth() / 2, // cx
                canvas.getHeight() / 2, // cy
                (int) (img_size / 2), // Radius
                paint // Paint
        );

        // Display the newly created bitmap on app interface
        // ImageView mImageView = findViewById(R.id.imageView);
        img_circles[img_count].setImageBitmap(bitmap);
    }
}
