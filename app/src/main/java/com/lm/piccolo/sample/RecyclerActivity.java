package com.lm.piccolo.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lm.piccolo.Piccolo;
import com.lm.piccolo.view.ConductorForAdapter;

public class RecyclerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ConductorForAdapter ConductorForAdapter = Piccolo.createForList(recyclerView);
        ConductorForAdapter
                .items(new int[]{R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another, R.layout.item, R.layout.item_another})
                .visible(true)
                .adapter(new Adapter())
                .play();

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConductorForAdapter.visible(false).play();
            }
        }, 5000);
    }

    static class Adapter extends RecyclerView.Adapter<Adapter.VH> {

        @Override
        public int getItemCount() {
            return 10;
        }

        @NonNull
        @Override
        public Adapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            if(viewType==0){
                return new VH(inflater.inflate(R.layout.item,parent,false));
            }else{
                return new VH(inflater.inflate(R.layout.item_another,parent,false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.VH holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            return position%2;
        }

        static class VH extends RecyclerView.ViewHolder {

            public VH(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
