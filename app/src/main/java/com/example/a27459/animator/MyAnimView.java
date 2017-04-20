package com.example.a27459.animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 27459 on 2017/4/20.
 */

public class MyAnimView extends View {

    private Paint mPaint;
    private Point currentPoint;
    public static final float RADIUS = 50f;

    public MyAnimView(Context context) {
        this(context,null);

    }

    public MyAnimView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null){
            currentPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();//第一次启动动画 后面会在动画中调用onDraw
        }else{
            drawCircle(canvas);
        }

    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);

    }

    private void startAnimation(){
        Point startPoint = new Point(RADIUS,RADIUS);
        Point endPoint = new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                currentPoint = (Point) animator.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(5000);
        anim.start();
    }

    public class PointEvaluator implements TypeEvaluator {

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
