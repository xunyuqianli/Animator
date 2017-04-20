package com.example.a27459.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
    }


    public void startAnim(View view) {
        float currentX = image.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(image, "translationX", currentX, -300f, currentX);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(image, "alpha", 1f, 0f,1f);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(alphaAnimator).with(rotationAnimator).after(animator);
        animSet.setDuration(5000);
        animSet.start();



    }

    public void startAnimXML(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.anim);
        animator.setTarget(image);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Toast.makeText(MainActivity.this,"开始动画",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startSupAnim(View view) {

    }
}
