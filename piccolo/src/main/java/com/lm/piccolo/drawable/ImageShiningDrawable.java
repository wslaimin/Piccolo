package com.lm.piccolo.drawable;

import android.graphics.Rect;

public class ImageShiningDrawable extends TextShiningDrawable {

    public ImageShiningDrawable() {
        super();
        setMaxLines(1);
    }

    @Override
    public void setMaxLines(int max) {
        super.setMaxLines(1);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        setShiningHeight(bounds.height() - getShiningPaddingTop() - getShiningPaddingBottom());
    }
}
