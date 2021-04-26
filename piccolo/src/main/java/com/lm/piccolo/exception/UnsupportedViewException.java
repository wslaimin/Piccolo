package com.lm.piccolo.exception;

public class UnsupportedViewException extends RuntimeException{
    public UnsupportedViewException() {
        super("view is neither AbsListView,ViewPager nor RecyclerView");
    }
}
