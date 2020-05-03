package com.example.circlepathtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Path;

import static android.graphics.Path.Direction.CW;


public class MainActivity extends AppCompatActivity {

    ObjectAnimator animator;
    int coordinates[] = new int[2];
    int coordinates2[] = new int[2];
    Canvas canvas = new Canvas();
    Button button;
    Path path = new Path();

    //boolean foo = false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View layout = findViewById(R.id.main_layout);

        final Button orbit = findViewById(R.id.orbitButton);
        final Button startbutton = findViewById(R.id.StartButton);
        button = findViewById(R.id.orbitButton);

        //get middle of screen coordinates
        final int mWidth= this.getResources().getDisplayMetrics().widthPixels;
        final int mHeight= this.getResources().getDisplayMetrics().heightPixels;

        //set square side lengths to be 1/4 of screen
        final float mWidthX = (float)mWidth/2f;
        final float mHeightY = (float)mHeight/2f;
        final float sidelength = mWidth/4f;

        final float object1left = mWidthX - sidelength;
        final float object1right = mWidthX + sidelength;
        final float object1top = mHeightY - sidelength;
        final float object1bottom = mHeightY + sidelength;

Log.d("", String.valueOf(object1left));
Log.d("", String.valueOf(object1right));
Log.d("", String.valueOf(object1bottom));
Log.d("", String.valueOf(object1top));


        layout.post(new Runnable() {
            @Override
            public void run()
            {
                button.getLocationOnScreen(coordinates);

/*
                float textviewX = coordinates[0];
                float textviewY = coordinates[1];
                startbutton.getLocationOnScreen(coordinates2);
                float startbuttonX = coordinates2[0];
                float startbuttonY = coordinates2[1];

                float sideLength = startbuttonY - textviewY;
                float top = textviewX;
                float left = startbuttonX - sideLength;
                float bottom = startbuttonY + sideLength;
                float right = startbuttonX + sideLength;



 */
                startbutton.setTranslationX(mWidthX);
                startbutton.setTranslationY(mHeightY);
                path.arcTo(0, object1top, 1000, object1bottom, 270f, 359f, true);



                animator = ObjectAnimator.ofFloat(button, View.X, View.Y, path);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(2000);


            }
        });


        startbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                animator.start();

            }
        });

    }
}
