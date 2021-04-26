package com.lm.piccolo.sample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.lm.piccolo.Piccolo;
import com.lm.piccolo.view.ConductorForView;
import com.lm.piccolo.view.PiccoloLayout;

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        PiccoloLayout piccoloText=findViewById(R.id.Piccolo_text);
        PiccoloLayout piccoloImage=findViewById(R.id.Piccolo_image);
        ConductorForView conductor1=Piccolo.createForView(piccoloText).visible(true);
        conductor1.play();
        ConductorForView conductor2=Piccolo.createForView(piccoloImage).visible(true);
        conductor2.play();

        piccoloText.postDelayed(new Runnable() {
            @Override
            public void run() {
                conductor1.visible(false).play();
                conductor2.visible(false).play();
            }
        },5000);
    }
}
