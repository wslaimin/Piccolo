package com.lm.piccolo.sample;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lm.piccolo.Piccolo;
import com.lm.piccolo.view.ConductorForAdapter;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = findViewById(R.id.list);
        ConductorForAdapter ConductorForAdapter = Piccolo.createForList(listView);
        ConductorForAdapter
                .items(new int[]{R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another})
                .adapter(new Adapter(this))
                .visible(true)
                .play();

        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConductorForAdapter.visible(false).play();
            }
        }, 5000);
    }

    static class Adapter extends BaseAdapter {
        Context mContext;

        Adapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                if (getItemViewType(position) == 0) {
                    convertView = inflater.inflate(R.layout.item, null);
                } else {
                    convertView = inflater.inflate(R.layout.item_another, null);
                }
            }
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }
}
