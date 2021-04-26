package com.lm.piccolo.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;

public class ShiningDrawable extends ShapeDrawable implements Shining {
    private Shader mShader;
    private final Matrix mMatrix;
    private final ValueAnimator mAnimator;
    private boolean mPendingAnimator;
    private boolean mStarted;

    public ShiningDrawable() {
        this(new RectShape());
    }

    public ShiningDrawable(Shape s) {
        super(s);
        mMatrix = new Matrix();
        ValueAnimator.AnimatorUpdateListener listener = animation -> {
            mMatrix.setTranslate((Integer) animation.getAnimatedValue(), 0);
            if (mShader != null) {
                mShader.setLocalMatrix(mMatrix);
            }
            invalidateSelf();
        };
        mAnimator = ValueAnimator.ofInt().setDuration(1000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.addUpdateListener(listener);
        setShape(new RectShape());
    }

    @Override
    public void setDuration(long d) {
        mAnimator.setDuration(d);
    }

    @Override
    public long getDuration() {
        return mAnimator.getDuration();
    }

    @Override
    public void setShader(Shader shader) {
        mShader = shader;
        invalidateSelf();
    }

    @Override
    public void start() {
        mStarted = true;
        if (getBounds().isEmpty()) {
            mPendingAnimator = true;
        } else {
            mAnimator.start();
        }
    }

    @Override
    public void cancel() {
        mStarted = false;
        mPendingAnimator = false;
        if (mAnimator.isStarted()) {
            mAnimator.cancel();
        }
    }

    @Override
    public boolean isStarted() {
        return mStarted;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mAnimator.setIntValues(-bounds.width(), bounds.width());
        if (mStarted && mPendingAnimator) {
            mAnimator.start();
            mPendingAnimator = false;
        }
    }

    @Override
    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
        super.onDraw(shape, canvas, paint);
        paint.setShader(mShader);
        getShape().draw(canvas, paint);
    }
}
