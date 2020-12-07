package com.example.propertyanimaition;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button button_up, button_down, button_left, button_right, button_DL, button_DR, button_UL, button_UR, button_reset,
            button_stop,button_run;
    private ImageView imageView_stand;
    private String TAG = "main";
    private float x, y,x_location,y_location;
    private AnimationDrawable animDrew;

    private void SetView() {
        button_up = findViewById(R.id.button_up);
        button_down = findViewById(R.id.button_down);
        button_left = findViewById(R.id.button_left);
        button_right = findViewById(R.id.button_right);
        button_DL = findViewById(R.id.button_DL);
        button_DR = findViewById(R.id.button_DR);
        button_UL = findViewById(R.id.button_UL);
        button_UR = findViewById(R.id.button_UR);
        button_reset = findViewById(R.id.button_reset);
        button_stop = findViewById(R.id.button_stop);
        button_run = findViewById(R.id.button_run);

        imageView_stand = findViewById(R.id.imageView_stand);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetView();
        //紀錄初始圖片位置
        x = imageView_stand.getX();
        y = imageView_stand.getY();
        //紀錄變動位置
        y_location = x;
        x_location = y;
        //執行動畫
        animDrew = (AnimationDrawable) imageView_stand.getDrawable();
        animDrew.selectDrawable(1);
        //設定按鈕監聽
        SetButton();

    }

    private void SetButton() {
        button_up.setOnClickListener(new MyButton());
        button_down.setOnClickListener(new MyButton());
        button_left.setOnClickListener(new MyButton());
        button_right.setOnClickListener(new MyButton());
        button_DL.setOnClickListener(new MyButton());
        button_DR.setOnClickListener(new MyButton());
        button_UL.setOnClickListener(new MyButton());
        button_UR.setOnClickListener(new MyButton());
        button_reset.setOnClickListener(new MyButton());
        button_stop.setOnClickListener(new MyButton());
        button_run.setOnClickListener(new MyButton());
    }


    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            animDrew.start();
            switch (v.getId()) {
                case R.id.button_up:
                    playPropertyAnimation("translationY", y_location -= 100);
                    break;
                case R.id.button_down:
                    playPropertyAnimation("translationY", y_location += 100);
                    break;
                case R.id.button_left:
                    playPropertyAnimation("translationX", x_location -= 100);
                    break;
                case R.id.button_right:
                    playPropertyAnimation("translationX", x_location += 100);
                    break;
                case R.id.button_DL:
                    playPropertyAnimationXY("translationX", "translationY", x_location -= 100, y_location += 100);
                    break;
                case R.id.button_DR:
                    playPropertyAnimationXY("translationX", "translationY", x_location += 100, y_location += 100);
                    break;
                case R.id.button_UL:
                    playPropertyAnimationXY("translationX", "translationY", x_location -= 100, y_location -= 100);
                    break;
                case R.id.button_UR:
                    playPropertyAnimationXY("translationX", "translationY", x_location += 100, y_location -= 100);
                    break;
                case R.id.button_reset:
                    playPropertyAnimationXY("translationX", "translationY", x, y);
                    x_location=x;
                    y_location=y;
                    animDrew.selectDrawable(1);
                    animDrew.stop();
                    break;
                case R.id.button_stop:
                    animDrew.stop();
                    animDrew.selectDrawable(1);
                    break;
                case R.id.button_run:
                    animDrew.start();
                    break;
            }
            Log.d(TAG, "x = "+x_location+" y = "+y_location);
        }
    }

    //TODO:單一方向移動
    void playPropertyAnimation(String propertyName, float distance) {
        /**參數1：將動畫作用在這個view上
         * 參數2：指定動畫種類
         * 參數3：動畫移動量的多寡
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView_stand, propertyName, distance);
        animator.setDuration(1000);//設定動畫間隔為1秒
        animator.start();
    }

    //TODO:組合動畫 組合方向移動
    void playPropertyAnimationXY(String X, String Y, float distanceX, float distanceY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView_stand, X, distanceX);
        animatorX.setDuration(1000);//設定動畫間隔為1秒

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView_stand, Y, distanceY);
        animatorY.setDuration(1000);//設定動畫間隔為1秒

        //動畫組合Set
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorX).with(animatorY);
        animatorSet.start();

    }


    public void playDrawableAnimation() {

    }
}