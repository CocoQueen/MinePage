package com.example.coco.minepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by coco on 2017/10/10.
 */

public class MineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int HEAD = 101;
    public final int LIST = 102;
    public Context context;
    private LayoutInflater inflater;
    public HeadViewHolder headViewHolder;
    public ListViewHolder listViewHolder;

    public MineAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            View inflate = inflater.inflate(R.layout.head, parent, false);
            headViewHolder = new HeadViewHolder(inflate);
            return headViewHolder;

        } else {
            View inflate = inflater.inflate(R.layout.list, parent, false);
            listViewHolder = new ListViewHolder(inflate);
            return listViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD;
        else
            return LIST;
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.mImg);
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTv= (TextView) itemView.findViewById(R.id.mTv);
        }
    }
}
