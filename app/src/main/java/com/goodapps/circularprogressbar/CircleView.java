package com.goodapps.circularprogressbar;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private static final int ARC_FULL_ROTATION_DEGREE = 360;
    private static final float PERCENTAGE_DIVIDER = 100.0f;
    private static final String PERCENTAGE_VALUE_HOLDER = "percentage";

    private RectF ovalSpace = new RectF();

    private int currentPercentage;
    private int parentArcColor = Color.GRAY;
    private int fillArcColor = Color.GREEN;
    private float strokeWidth = 10f;
    private int size = 200;


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setSpace();
        drawBackgroundArc(canvas);
        drawInnerArc(canvas);
    }

    private Paint parentArcPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(parentArcColor);
        paint.setStrokeWidth(strokeWidth);

        return paint;
    }

    private Paint fillArcPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(fillArcColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);

        return paint;
    }


    private void setSpace() {
        float horizontalCenter = getWidth() / 2f;
        float verticalCenter = getHeight() / 2f;
        ovalSpace.set(
                horizontalCenter - size,
                verticalCenter - size,
                horizontalCenter + size,
                verticalCenter + size
        );
    }

    private float getPercentageToFill() {
        return ARC_FULL_ROTATION_DEGREE * (currentPercentage / PERCENTAGE_DIVIDER);
    }

    private void drawBackgroundArc(Canvas canvas) {
        canvas.drawArc(ovalSpace, 0f, 360f, false, parentArcPaint());
    }

    private void drawInnerArc(Canvas canvas) {
        canvas.drawArc(ovalSpace, 90f, getPercentageToFill(), false, fillArcPaint());
    }

    public void animateProgress() {
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat(PERCENTAGE_VALUE_HOLDER, 0f, 100f);
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(valuesHolder);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float percentage = (float) valueAnimator.getAnimatedValue(PERCENTAGE_VALUE_HOLDER);
                currentPercentage = (int) percentage;
                invalidate();
            }
        });

        animator.start();
    }
}
