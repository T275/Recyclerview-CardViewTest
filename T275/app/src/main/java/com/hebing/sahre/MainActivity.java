package com.hebing.sahre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.animation.TranslateAnimation;

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
        mRecyclerView.setItemAnimator(new SimpleItemAnimator() {
            View view = null;

            @Override
            public boolean animateRemove(RecyclerView.ViewHolder holder) {
                view = holder.itemView;
                System.out.println(">>>>>>>>>");
                return true;
            }

            @Override
            public boolean animateAdd(RecyclerView.ViewHolder holder) {
                System.out.println(">>>>>>>>>");
                return false;
            }

            @Override
            public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                System.out.println(">>>>>>>>>");
                return false;
            }

            @Override
            public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
                System.out.println(">>>>>>>>>");
                return false;
            }

            @Override
            public void runPendingAnimations() {
                TranslateAnimation animation = new TranslateAnimation(0, view.getWidth(), 0, 0);
                animation.setDuration(1000);
                animation.setFillAfter(false);
                animation.setRepeatCount(1);
                view.startAnimation(animation);
                System.out.println(">>>>>>>>>");
            }

            @Override
            public void endAnimation(RecyclerView.ViewHolder item) {
                System.out.println(">>>>>>>>>");
            }

            @Override
            public void endAnimations() {
                System.out.println(">>>>>>>>>");
            }

            @Override
            public boolean isRunning() {
                System.out.println(">>>>>>>>>");
                return false;
            }
        });
        mRecyclerView.setAdapter(mAdapter = new MainActivityAdapter(mItems, this));
        mAdapter.setOnItemClickListener(new MainActivityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
//                startActivity(new Intent(MainActivity.this, mItems.get(pos)));
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
