package com.lm.piccolo.view;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.lm.piccolo.drawable.ShiningPadding;
import java.util.ArrayList;
import java.util.List;

public class ConductorForView {
    private final View mView;
    private boolean mShining;
    private Drawable mMask;
    private boolean mVisible;
    private int mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom;

    public ConductorForView(PiccoloLayout view) {
        mView = view;
        mMask = view.getMaskDrawable();
        mShining = view.getShining();
        if (mMask instanceof ShiningPadding) {
            ShiningPadding shiningPadding = (ShiningPadding) mMask;
            mPaddingLeft = shiningPadding.getShiningPaddingLeft();
            mPaddingTop = shiningPadding.getShiningPaddingTop();
            mPaddingRight = shiningPadding.getShiningPaddingRight();
            mPaddingBottom = shiningPadding.getShiningPaddingBottom();
        }
    }

    public ConductorForView(ViewGroup viewGroup) {
        mView = viewGroup;
    }

    public ConductorForView shining(boolean shining) {
        mShining = shining;
        return this;
    }

    public ConductorForView mask(Drawable mask) {
        mMask = mask;
        return this;
    }

    public ConductorForView visible(boolean visible) {
        mVisible = visible;
        return this;
    }

    public ConductorForView paddingLeft(int padding) {
        mPaddingLeft = padding;
        return this;
    }

    public ConductorForView paddingTop(int padding) {
        mPaddingTop = padding;
        return this;
    }

    public ConductorForView paddingRight(int padding) {
        mPaddingRight = padding;
        return this;
    }

    public ConductorForView paddingBottom(int padding) {
        mPaddingBottom = padding;
        return this;
    }

    public void play() {
        if (mView instanceof PiccoloLayout) {
            play((PiccoloLayout) mView);
        } else {
            List<PiccoloLayout> views = new ArrayList<>();
            AdapterUtil.findPiccoloViews(mView, views);
            if (views.size() > 0) {
                for (PiccoloLayout v : views) {
                    play(v);
                }
            }
        }
    }

    private void play(PiccoloLayout piccolo) {
        piccolo.setShining(mShining);
        piccolo.setMaskDrawable(mMask);
        piccolo.setShiningPadding(mPaddingLeft,mPaddingTop,mPaddingRight,mPaddingBottom);
        if (mVisible) {
            piccolo.show();
        } else {
            piccolo.hide();
        }
    }
}

