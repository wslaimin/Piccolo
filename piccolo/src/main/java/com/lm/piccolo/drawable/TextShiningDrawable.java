package com.lm.piccolo.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TextShiningDrawable extends Drawable implements Drawable.Callback, Shining, ShiningPadding {
    private final TextShiningState mState;
    private final ShiningDrawable mDrawable;
    private Shader mShader;

    public TextShiningDrawable() {
        this(new TextShiningState());
    }

    private TextShiningDrawable(TextShiningState state) {
        mState = state;
        mState.mShinningHeight = 50;
        mState.mSpace = 16;
        mState.mMaxLines = Integer.MAX_VALUE;
        mDrawable = new ShiningDrawable();
        mDrawable.setCallback(this);
    }

    public void setShiningHeight(int height) {
        if (mState.mShinningHeight == height) {
            return;
        }
        mState.mShinningHeight = height;
        invalidateSelf();
    }

    public int getShiningHeight() {
        return mState.mShinningHeight;
    }

    @Override
    public void setShiningPadding(int left, int top, int right, int bottom) {
        if ((left | right | top | bottom) == 0) {
            mState.mPadding = null;
        } else {
            if (mState.mPadding == null) {
                mState.mPadding = new Rect();
            }
            mState.mPadding.left = left;
            mState.mPadding.right = right;
            mState.mPadding.top = top;
            mState.mPadding.bottom = bottom;
        }
        invalidateSelf();
    }

    @Override
    public int getShiningPaddingLeft() {
        if (mState.mPadding != null) {
            return mState.mPadding.left;
        }
        return 0;
    }

    @Override
    public int getShiningPaddingTop() {
        if (mState.mPadding != null) {
            return mState.mPadding.top;
        }
        return 0;
    }

    @Override
    public int getShiningPaddingRight() {
        if (mState.mPadding != null) {
            return mState.mPadding.right;
        }
        return 0;
    }

    @Override
    public int getShiningPaddingBottom() {
        if (mState.mPadding != null) {
            return mState.mPadding.bottom;
        }
        return 0;
    }

    public void setSpace(int space) {
        if (mState.mSpace == space) {
            return;
        }
        mState.mSpace = space;
        invalidateSelf();
    }

    public int getSpace() {
        return mState.mSpace;
    }

    public void setMaxLines(int max) {
        if (mState.mMaxLines == max) {
            return;
        }
        mState.mMaxLines = max;
        invalidateSelf();
    }

    public int getMaxLines() {
        return mState.mMaxLines;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int paddingTop = 0;
        int paddingBottom = 0;
        int paddingLeft = 0;
        int paddingRight = 0;
        if (mState.mPadding != null) {
            paddingTop = mState.mPadding.top;
            paddingBottom = mState.mPadding.bottom;
            paddingLeft = mState.mPadding.left;
            paddingRight = mState.mPadding.right;
        }
        int height = getBounds().height() - paddingTop - paddingBottom;
        int width = getBounds().width() - paddingLeft - paddingRight;
        int top = 0;

        int lines = 0;

        mDrawable.setBounds(paddingLeft, paddingTop + top, paddingLeft + width, paddingTop + top + mState.mShinningHeight);
        mDrawable.draw(canvas);

        while (top + mState.mShinningHeight <= height) {
            mDrawable.setBounds(paddingLeft, paddingTop + top, paddingLeft + width, paddingTop + top + mState.mShinningHeight);
            mDrawable.draw(canvas);
            top += mState.mShinningHeight + mState.mSpace;
            lines++;
            if (lines >= mState.mMaxLines) {
                break;
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mDrawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mDrawable.setColorFilter(colorFilter);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getOpacity() {
        return mDrawable.getOpacity();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        int paddingLeft = 0;
        int paddingRight = 0;
        if (mState.mPadding != null) {
            paddingLeft = mState.mPadding.left;
            paddingRight = mState.mPadding.right;
        }
        if (mShader == null) {
            mDrawable.setShader(new LinearGradient(0, 0, bounds.width() - paddingLeft - paddingRight, 0, new int[]{0xFFF6F6F6, 0xFFF0F0F0, 0xFFF6F6F6}, new float[]{0.08f, 0.18f, 0.33f}, Shader.TileMode.CLAMP));
        } else {
            mDrawable.setShader(mShader);
        }
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        invalidateSelf();
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        scheduleSelf(what, when);
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        unscheduleSelf(what);
    }

    @Nullable
    @Override
    public TextShiningState getConstantState() {
        return mState;
    }

    @Override
    public void setDuration(long d) {
        mDrawable.setDuration(d);
    }

    @Override
    public long getDuration() {
        return mDrawable.getDuration();
    }

    @Override
    public void setShader(Shader shader) {
        mShader = shader;
        mDrawable.setShader(shader);
    }

    @Override
    public void start() {
        mDrawable.start();
    }

    @Override
    public void cancel() {
        mDrawable.cancel();
    }

    @Override
    public boolean isStarted() {
        return mDrawable.isStarted();
    }

    static final class TextShiningState extends ConstantState {
        int mShinningHeight;
        int mSpace;
        int mMaxLines;
        Rect mPadding;

        TextShiningState() {
        }

        TextShiningState(TextShiningState state) {
            mShinningHeight = state.mShinningHeight;
            mSpace = state.mSpace;
            mMaxLines = state.mMaxLines;
            if (state.mPadding != null) {
                mPadding = new Rect(state.mPadding);
            }
        }

        @NonNull
        @Override
        public Drawable newDrawable() {
            return new TextShiningDrawable(new TextShiningState(this));
        }

        @Override
        public int getChangingConfigurations() {
            return 0;
        }
    }

}