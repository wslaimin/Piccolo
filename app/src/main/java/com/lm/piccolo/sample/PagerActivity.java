package com.lm.piccolo.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.lm.piccolo.Piccolo;
import com.lm.piccolo.view.ConductorForAdapter;

public class PagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ViewPager viewPager = findViewById(R.id.pager);
        ConductorForAdapter ConductorForAdapter = Piccolo.createForList(viewPager);
        ConductorForAdapter
                .items(new int[]{R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another, R.layout.item})
                .visible(true)
                .adapter(new Adapter())
                .play();
        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConductorForAdapter.visible(false).play();
            }
        },10000);
    }

    class Adapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view;
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            if(position%2==0) {
                view = inflater.inflate(R.layout.item, null);
            }else{
                view=inflater.inflate(R.layout.item_another, null);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

}
