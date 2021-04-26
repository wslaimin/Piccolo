package com.lm.piccolo.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        if (id == R.id.list_sample) {
            intent = new Intent(this, ListActivity.class);
        } else if (id == R.id.pager_sample) {
            intent = new Intent(this, PagerActivity.class);
        } else if (id == R.id.recycler_sample) {
            intent = new Intent(this, RecyclerActivity.class);
        } else if (id == R.id.view_sample) {
            intent = new Intent(this, ViewActivity.class);
        }
        startActivity(intent);
    }
}