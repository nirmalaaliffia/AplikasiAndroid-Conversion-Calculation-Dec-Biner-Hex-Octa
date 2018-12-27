package com.binaryconvert.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {
    private TextView tvSplash;
    private ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splahscreen);

        tvSplash = (TextView) findViewById(R.id.textViewSplash);
        ivSplash = (ImageView) findViewById(R.id.imageViewSplash);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tvSplash.startAnimation(myanim);
        ivSplash.startAnimation(myanim);

        final Intent intent2 = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent2);
                    finish();
                }
            }
        };
        timer.start();
    }
}
