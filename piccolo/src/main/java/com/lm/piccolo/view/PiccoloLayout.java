package com.lm.piccolo.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.lm.Piccolo.R;
import com.lm.piccolo.drawable.Shining;
import com.lm.piccolo.drawable.ShiningPadding;

public class PiccoloLayout extends FrameLayout implements ShiningPadding {
    private Drawable mMaskDrawable;
    private boolean mShowing;
    private boolean mShining;
    private int mShiningPaddingLeft, mShiningPaddingRight, mShiningPaddingTop, mShiningPaddingBottom;

    public PiccoloLayout(@NonNull Context context) {
        this(context, null);
    }

    public PiccoloLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PiccoloLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PiccoloLayout, defStyleAttr, 0);
        parseAttrs(a);
        a.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PiccoloLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PiccoloLayout, defStyleAttr, defStyleRes);
        parseAttrs(a);
        a.recycle();
    }

    public void setMaskDrawable(Drawable drawable) {
        mMaskDrawable = drawable;
        if (mShowing) {
            setForeground(mMaskDrawable);
        }
    }

    public Drawable getMaskDrawable() {
        return mMaskDrawable;
    }

    public void setShining(boolean shining) {
        if (mShining == shining) {
            return;
        }
        mShining = shining;
        if (mMaskDrawable instanceof Shining) {
            Shining drawable = ((Shining) mMaskDrawable);
            if (mShining && !drawable.isStarted()) {
                drawable.start();
            } else if (!mShining && drawable.isStarted()) {
                drawable.cancel();
            }
        }
    }

    public boolean getShining() {
        return mShining;
    }

    public void show() {
        if (mShowing) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.INVISIBLE);
        }
        mShowing = true;
        if (mMaskDrawable instanceof Shining) {
            Shining drawable = ((Shining) mMaskDrawable);
            if (mShining && !drawable.isStarted()) {
                drawable.start();
            }
        }
        setForeground(mMaskDrawable);
    }

    public void hide() {
        if (!mShowing) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(View.VISIBLE);
        }
        mShowing = false;
        if (mMaskDrawable instanceof Shining) {
            Shining drawable = ((Shining) mMaskDrawable);
            if (mShining && drawable.isStarted()) {
                drawable.cancel();
            }
        }
        setForeground(null);
    }

    private void parseAttrs(TypedArray a) {
        Drawable drawable = null;
        try {
            drawable = a.getDrawable(R.styleable.PiccoloLayout_mask);
        } catch (Resources.NotFoundException exception) {
            try {
                Class<?> clazz = Class.forName(a.getString(R.styleable.PiccoloLayout_mask));
                drawable = (Drawable) clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setMaskDrawable(drawable);
        setShining(a.getBoolean(R.styleable.PiccoloLayout_shining, true));

        int paddingLeft = a.getDimensionPixelSize(R.styleable.PiccoloLayout_shining_paddingLeft, 0);
        int paddingRight = a.getDimensionPixelSize(R.styleable.PiccoloLayout_shining_paddingRight, 0);
        int paddingTop = a.getDimensionPixelSize(R.styleable.PiccoloLayout_shining_paddingTop, 0);
        int paddingBottom = a.getDimensionPixelSize(R.styleable.PiccoloLayout_shining_paddingBottom, 0);
        setShiningPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mMaskDrawable instanceof Shining) {
            Shining drawable = (Shining) mMaskDrawable;
            if (mShowing && mShining && !drawable.isStarted()) {
                drawable.start();
            }
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState state = new SavedState(superState);
        state.mShowing = mShowing;
        state.mShining = mShining;
        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setShining(ss.mShining);
        if (ss.mShowing) {
            show();
        } else {
            hide();
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mMaskDrawable instanceof Shining) {
            Shining drawable = (Shining) mMaskDrawable;
            if (mShowing && mShining && drawable.isStarted()) {
                drawable.cancel();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mShowing) {
            return true;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public void setShiningPadding(int left, int top, int right, int bottom) {
        mShiningPaddingLeft = left;
        mShiningPaddingTop = top;
        mShiningPaddingRight = right;
        mShiningPaddingBottom = bottom;
        if(mMaskDrawable instanceof ShiningPadding){
            ((ShiningPadding) mMaskDrawable).setShiningPadding(left,top,right,bottom);
        }
    }

    @Override
    public int getShiningPaddingLeft() {
        return mShiningPaddingLeft;
    }

    @Override
    public int getShiningPaddingTop() {
        return mShiningPaddingTop;
    }

    @Override
    public int getShiningPaddingRight() {
        return mShiningPaddingRight;
    }

    @Override
    public int getShiningPaddingBottom() {
        return mShiningPaddingBottom;
    }

    static class SavedState extends BaseSavedState {
        boolean mShowing;
        boolean mShining;
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };

        private SavedState(Parcel source) {
            super(source);
            mShining = source.readByte() == 1;
            mShowing = source.readByte() == 1;
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte(mShowing ? (byte) 1 : (byte) 0);
            out.writeByte(mShining ? (byte) 1 : (byte) 0);
        }
    }
}
