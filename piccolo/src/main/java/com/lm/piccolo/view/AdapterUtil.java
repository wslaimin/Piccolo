package com.lm.piccolo.view;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

class AdapterUtil {
    static SparseIntArray getViewTypes(int[] ids) {
        if (ids == null || ids.length == 0) {
            return null;
        }
        SparseIntArray viewTypes = new SparseIntArray();
        for (Integer id : ids) {
            if (viewTypes.indexOfValue(id) != -1) {
                continue;
            }
            viewTypes.put(viewTypes.size(), id);
        }
        return viewTypes;
    }

    static void findPiccoloViews(View view,List<PiccoloLayout> views){
        if(view instanceof PiccoloLayout){
            views.add((PiccoloLayout)view);
        }else if(view instanceof ViewGroup){
            ViewGroup viewGroup=(ViewGroup)view;
            for(int i=0;i<viewGroup.getChildCount();i++) {
                findPiccoloViews(viewGroup.getChildAt(i),views);
            }
        }
    }
}
