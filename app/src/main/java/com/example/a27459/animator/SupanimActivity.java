package com.example.a27459.animator;

import android.animation.TypeEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SupanimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supanim);

    }

    public class PointEvaluator implements TypeEvaluator{

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            float x = startPoint.getY() +fraction*(endPoint.getX() - startPoint.getX());
            float y = startPoint.getY() +fraction*(endPoint.getY()- startPoint.getY());
            Point point = new Point(x,y);
            return point;
        }
    }


}
