package com.lm.piccolo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;

class PiccoloAdapter3 extends PagerAdapter {
    private final ViewSpec[] mViewSpecs;

    PiccoloAdapter3(ViewSpec[] viewSpecs) {
        mViewSpecs=viewSpecs;
    }

    @Override
    public int getCount() {
        return mViewSpecs == null ? 0 : mViewSpecs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(mViewSpecs[position].mId, null);
        container.addView(view);
        List<PiccoloLayout> views = new ArrayList<>();
        AdapterUtil.findPiccoloViews(view, views);
        for (PiccoloLayout v : views) {
            v.show();
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
