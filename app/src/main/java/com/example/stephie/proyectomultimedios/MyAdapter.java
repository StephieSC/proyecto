package com.example.stephie.proyectomultimedios;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stephie.proyectomultimedios.Models.Detalle;

import java.util.List;

/**
 * Created by Stephie on 06-12-2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Detalle> mData;

    public MyAdapter(List<Detalle> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cellview,
                parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Detalle dato = mData.get(position);

        holder.fecha.setText("Fecha: "+dato.getDate());
        holder.hora.setText("Hora: "+dato.getHour());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fecha;
        private TextView hora;

        public ViewHolder(View itemView) {
            super(itemView);
            fecha = (TextView) itemView.findViewById(R.id.detalle_dia);
            hora = (TextView) itemView.findViewById(R.id.detalle_hora);

        }
    }

}
