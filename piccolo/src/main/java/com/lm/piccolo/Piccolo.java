package com.lm.piccolo;

import android.view.View;
import com.lm.piccolo.view.ConductorForAdapter;
import com.lm.piccolo.view.ConductorForView;
import com.lm.piccolo.view.PiccoloLayout;

public class Piccolo {
    private Piccolo() {
    }

    public static ConductorForAdapter createForList(View view) {
        return new ConductorForAdapter(view);
    }

    public static ConductorForView createForView(PiccoloLayout view) {
        return new ConductorForView(view);
    }
}
