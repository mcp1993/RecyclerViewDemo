package com.mcp1993.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by mcp1993 on 2016/10/9 0009.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<Integer> resIds;
    public MyAdapter(List<Integer> resIds){
        this.resIds = resIds;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(resIds.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(holder,position);
                }
            }
        });

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onLongClick(holder,position);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return resIds.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }


    interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder VH ,int position);
    }

    interface OnItemLongClickListener {
        void onLongClick(RecyclerView.ViewHolder VH,int position);
    }
}
