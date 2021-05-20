package com.lm.piccolo.drawable;

public interface Shining {
    void setDuration(long d);

    long getDuration();

    void start();

    void cancel();

    boolean isStarted();
}
