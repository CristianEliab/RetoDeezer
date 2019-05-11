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
import com.appmoviles.retomoviles.model.Cancion;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCanciones extends RecyclerView.Adapter<AdapterCanciones.CustomViewHolder> {

    //Los datos que vamos a mostrar (View)
    private ArrayList<Cancion> data;
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

    public AdapterCanciones() {
        data = new ArrayList<>();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cancion_item, parent, false);
        manage = new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        ((TextView) holder.root.findViewById(R.id.tv_nombre_cansion)).setText(data.get(position).getNombreCancion());
        ((TextView) holder.root.findViewById(R.id.tv_nombre_artista)).setText(data.get(position).getNombreArtista());
        ((TextView) holder.root.findViewById(R.id.tv_anio_lanzamiento)).setText(data.get(position).getAnioLanzamiento());

        ImageView img = holder.root.findViewById(R.id.iv_icon_cansion);
        Glide.with(holder.root.getContext()).load(data.get(position).getIcon()).into(img);

        holder.root.findViewById(R.id.ll_item_cancion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                listener.onItemClick(data.get(position));
            }
        });

    }

    //OBSERVER
    public interface OnItemClickListener {
        void onItemClick(Cancion cancion);
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void agregarCancion(Cancion cansion) {
        data.add(cansion);
        notifyDataSetChanged();
    }
}