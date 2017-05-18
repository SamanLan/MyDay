package com.samanlan.lib_ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        final View logo = findViewById(R.id.img_logo);
        ObjectAnimator logoAnimatorX = ObjectAnimator.ofFloat(logo, "ScaleX", 0f, 0.5f, 1.2f, 1.0f);
        ObjectAnimator logoAnimatorY = ObjectAnimator.ofFloat(logo, "ScaleY", 0f, 0.5f, 1.2f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(logoAnimatorX, logoAnimatorY);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                logo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });
        set.setDuration(1500);
        set.setInterpolator(new LinearInterpolator());
        set.setStartDelay(500);
        set.start();
    }
}
