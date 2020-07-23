package com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teamwork.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
//    private String[] data;
    // RecyclerView recyclerView;
    ArrayList<Fragment3.Instancefield> datas = new ArrayList<>();
    public RecyclerAdapter() {

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText( datas.get(position).getTitle());
        Picasso.get()
                .load(datas.get(position).getUrl())
                .into(holder.imgShow);
        holder.id.setText(Integer.toString(datas.get(position).getId()));
    }


    public void setDatas(ArrayList<Fragment3.Instancefield> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgShow;
        public TextView title;
        public TextView id;
        public ViewHolder(View itemView) {
            super(itemView);
         this.imgShow = (ImageView) itemView.findViewById(R.id.imgShow);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.id = (TextView) itemView.findViewById(R.id.idText);
        }
    }
}

