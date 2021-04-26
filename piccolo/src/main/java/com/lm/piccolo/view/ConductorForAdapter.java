package com.lm.piccolo.view;

import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.lm.piccolo.exception.UnsupportedViewException;

public class ConductorForAdapter {
    private final View mView;
    private ViewSpec[] mItems;
    private RecyclerView.Adapter<?> mRecyclerAdapter;
    private PagerAdapter mPagerAdapter;
    private BaseAdapter mListAdapter;
    private boolean mVisible;

    public ConductorForAdapter(View view) {
        mView = view;
    }

    public ConductorForAdapter items(int[] items) {
        if (items != null) {
            mItems = new ViewSpec[items.length];
            for (int i = 0; i < mItems.length; i++) {
                mItems[i] = new ViewSpec(items[i]);
            }
        }
        return this;
    }

    public ConductorForAdapter items(ViewSpec[] items) {
        mItems = items;
        return this;
    }

    public ConductorForAdapter adapter(RecyclerView.Adapter<?> adapter) {
        mRecyclerAdapter = adapter;
        return this;
    }

    public ConductorForAdapter adapter(PagerAdapter adapter) {
        mPagerAdapter = adapter;
        return this;
    }

    public ConductorForAdapter adapter(BaseAdapter adapter) {
        mListAdapter = adapter;
        return this;
    }

    public ConductorForAdapter visible(boolean visible) {
        mVisible=visible;
        return this;
    }


    public void play(){
        if(mVisible){
            if (mView instanceof RecyclerView) {
                ((RecyclerView) mView).setAdapter(new PiccoloAdapter1(mItems));
            } else if (mView instanceof ViewPager) {
                ((ViewPager) mView).setAdapter(new PiccoloAdapter3(mItems));
            } else if (mView instanceof AbsListView) {
                ((AbsListView) mView).setAdapter(new PiccoloAdapter2(mView.getContext(), mItems));
            } else {
                throw new UnsupportedViewException();
            }
        }else{
            if (mView instanceof RecyclerView) {
                ((RecyclerView) mView).setAdapter(mRecyclerAdapter);
            } else if (mView instanceof ViewPager) {
                ((ViewPager) mView).setAdapter(mPagerAdapter);
            } else if (mView instanceof AbsListView) {
                ((AbsListView) mView).setAdapter(mListAdapter);
            } else {
                throw new UnsupportedViewException();
            }
        }
    }
}
