package com.lm.piccolo.drawable;

import android.graphics.Shader;

public interface Shining {
    void setDuration(long d);

    long getDuration();

    void setShader(Shader shader);

    void start();

    void cancel();

    boolean isStarted();
}
