package com.appmoviles.retomoviles.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appmoviles.retomoviles.R;
import com.appmoviles.retomoviles.model.PlayList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterPlayList extends RecyclerView.Adapter<AdapterPlayList.CustomViewHolder> {

    //Los datos que vamos a mostrar (View)
    private ArrayList<PlayList> data;
    private LinearLayoutManager manage;
    int index = -1;


    //Renglon y construccion
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;

        public CustomViewHolder(LinearLayout v) {
            super(v);
            root = v;
        }
    }

    public AdapterPlayList() {
        data = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_item, parent, false);
        manage = new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        ((TextView) holder.root.findViewById(R.id.tv_nombre_play_list)).setText(data.get(position).getNombrePlayList());
        ((TextView) holder.root.findViewById(R.id.tv_nombre_creador)).setText(data.get(position).getNombreCreador());
        ((TextView) holder.root.findViewById(R.id.tv_numero_cansiones)).setText(data.get(position).getNumeroCansiones());


        ImageView img = holder.root.findViewById(R.id.iv_icon_play_list);
        Glide.with(holder.root.getContext()).load(data.get(position).getIcon_play_list()).into(img);

        holder.root.findViewById(R.id.ll_item_play_list).setOnClickListener(v -> {
            index = position;
            listener.onItemClick(data.get(position));
        });

    }

    //OBSERVER
    public interface OnItemClickListener {
        void onItemClick(PlayList playList);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void agregarPlayList(PlayList playList) {
        data.add(playList);
        notifyDataSetChanged();
    }
}
