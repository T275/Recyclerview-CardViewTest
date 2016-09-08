package com.hebing.sahre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hebing.sahre.adapter.MainActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MainActivityAdapter mAdapter;
    List<Class> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Activity列表");
        initData();
        initView();
    }

    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MainActivityAdapter(mItems, this));
        mAdapter.setOnItemClickListener(new MainActivityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                startActivity(new Intent(MainActivity.this, mItems.get(pos)));
                mItems.remove(pos);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initData() {
        mItems = new ArrayList<>();
        mItems.add(LoginActivity.class);
        mItems.add(DrawerActivity.class);
        mItems.add(FullscreenActivity.class);
        mItems.add(ItemListActivity.class);
        mItems.add(MapsActivity.class);
        mItems.add(ScrollingActivity.class);
        mItems.add(SettingsActivity.class);
        mItems.add(TabledActivity.class);
    }
}
