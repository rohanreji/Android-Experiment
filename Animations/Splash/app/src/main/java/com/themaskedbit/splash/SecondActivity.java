package com.themaskedbit.splash;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        final ImageView im = findViewById(R.id.imgView);
        final ImageView im1 = findViewById(R.id.imgView1);
        im.setAlpha(0.7f);
        im1.setAlpha(0.7f);
        final ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = im.getWidth();
                final float translationX = width * progress;
                im.setTranslationX(translationX);
                im1.setTranslationX(translationX-width);
            }
        });
        animator.start();

    }
}
