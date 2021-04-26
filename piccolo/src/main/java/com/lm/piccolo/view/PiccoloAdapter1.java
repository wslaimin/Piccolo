package com.lm.piccolo.view;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

class PiccoloAdapter1 extends RecyclerView.Adapter<PiccoloAdapter1.VH> {
    private final ViewSpec[] mViewSpecs;
    private final SparseIntArray mViewTypes;

    public PiccoloAdapter1(ViewSpec[] viewSpecs) {
        mViewSpecs = viewSpecs;
        int[] viewIds = new int[mViewSpecs.length];
        for (int i = 0; i < mViewSpecs.length; i++) {
            viewIds[i] = mViewSpecs[i].mId;
        }
        mViewTypes = AdapterUtil.getViewTypes(viewIds);
    }

    @Override
    public int getItemCount() {
        return mViewSpecs == null ? 0 : mViewSpecs.length;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new VH(inflater.inflate(mViewTypes.get(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (holder.mViews != null) {
            for (PiccoloLayout view : holder.mViews) {
                view.show();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypes.indexOfValue(mViewSpecs[position].mId);
    }

    static class VH extends RecyclerView.ViewHolder {
        List<PiccoloLayout> mViews;

        public VH(@NonNull View itemView) {
            super(itemView);
            mViews = new ArrayList<>();
            AdapterUtil.findPiccoloViews(itemView, mViews);
        }
    }
}
