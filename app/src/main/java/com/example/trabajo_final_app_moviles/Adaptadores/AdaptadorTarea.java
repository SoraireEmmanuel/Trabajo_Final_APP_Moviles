package com.example.trabajo_final_app_moviles.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo_final_app_moviles.R;
import com.example.trabajo_final_app_moviles.entidades.Tareas;

import java.util.ArrayList;
import java.util.Date;

public class AdaptadorTarea extends RecyclerView.Adapter<AdaptadorTarea.ViewHolder> {
    ArrayList<Tareas> listDatos;
    final private ListClick mOnClickListener;

    public AdaptadorTarea(ArrayList<Tareas> listDatos, ListClick listener) {
        this.listDatos = listDatos;
        mOnClickListener=listener;
    }

    public interface ListClick{
        void onListClick(Integer id, String titulo, String prioridad, String fechaCreacion );
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tareas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titulo, prioridad, fecha, id;
        Date fechaDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=(TextView) itemView.findViewById(R.id.tarea_titulo_recicle);
            prioridad =(TextView) itemView.findViewById(R.id.prioridad_tarea_recicle);
            fecha=(TextView) itemView.findViewById(R.id.fecha_tarea_recicle);
            id=(TextView) itemView.findViewById(R.id.tarea_id_recicle);
            itemView.setOnClickListener(this);
        }

        public void asignarDatos(Tareas tarea) {
            titulo.setText(tarea.getTitulo());
            prioridad.setText(tarea.getPrioridad());
            fecha.setText(tarea.getFechaCreacion().toString());
            id.setText(String.valueOf(tarea.getId()));
        }
        @Override
        public void onClick(View v) {
            CharSequence value1 = id.getText().toString();
            Integer identificador = Integer.parseInt((value1.toString()));
            String tit = titulo.getText().toString();
            String pri = prioridad.getText().toString();
            String fechaCreacion = fecha.getText().toString();
            mOnClickListener.onListClick(identificador, tit, pri, fechaCreacion);
        }
    }

}
