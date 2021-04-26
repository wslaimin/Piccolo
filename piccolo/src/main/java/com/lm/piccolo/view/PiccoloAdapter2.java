package com.lm.piccolo.view;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

class PiccoloAdapter2 extends BaseAdapter {
    private final ViewSpec[] mViewSpecs;
    private final SparseIntArray mViewTypes;
    private final Context mContext;

    public PiccoloAdapter2(Context context, ViewSpec[] viewSpecs) {
        mContext = context;
        mViewSpecs = viewSpecs;
        int[] viewIds = new int[mViewSpecs.length];
        for (int i = 0; i < mViewSpecs.length; i++) {
            viewIds[i] = mViewSpecs[i].mId;
        }
        mViewTypes = AdapterUtil.getViewTypes(viewIds);
    }

    @Override
    public int getCount() {
        return mViewTypes == null ? 0 : mViewSpecs.length;
    }

    @Override
    public Object getItem(int position) {
        return mViewSpecs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mViewSpecs[position].mId, parent, false);
            List<PiccoloLayout> views = new ArrayList<>();
            AdapterUtil.findPiccoloViews(convertView, views);
            convertView.setTag(new VH(views));
        }
        VH holder=(VH)convertView.getTag();
        for(PiccoloLayout view : holder.mViews){
            view.show();
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypes.indexOfValue(mViewSpecs[position].mId);
    }

    @Override
    public int getViewTypeCount() {
        return mViewTypes.size();
    }

    static class VH {
        List<PiccoloLayout> mViews;

        public VH(List<PiccoloLayout> views) {
            mViews = views;
        }
    }
}
