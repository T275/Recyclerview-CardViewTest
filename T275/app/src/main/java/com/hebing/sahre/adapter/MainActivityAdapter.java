package com.hebing.sahre.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hebing.sahre.R;

import java.util.List;

/**
 * Created by Bin on 2016/8/29.
 */
public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHodler> implements View.OnClickListener {
    OnRecyclerViewItemClickListener onItemClickListener = null;
    List<Class> mItems = null;
    Context context;

    public MainActivityAdapter(List<Class> mItems, Context context) {
        this.mItems = mItems;
        this.context = context;
    }

    @Override
    public MainActivityAdapter.MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recyclerview_style_mainactivity, parent, false);
        view.setOnClickListener(this);
        MyViewHodler hodler = new MyViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(MainActivityAdapter.MyViewHodler holder, int position) {
        holder.mTextView.setText(mItems.get(position).getSimpleName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(view, (int) view.getTag());
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int pos);
    }

    class MyViewHodler extends RecyclerView.ViewHolder{
        TextView mTextView;
        public MyViewHodler(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
