package com.example.groovelog.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Artista;

import java.util.List;

public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder> {

    private List<Artista> listaArtistas;

    public ArtistaAdapter(List<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    @NonNull
    @Override
    public ArtistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artista, parent, false);
        return new ArtistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistaViewHolder holder, int position) {
        Artista artista = listaArtistas.get(position);
        holder.textNome.setText(artista.nome);
        holder.textGenero.setText(artista.genero);

        // Ação de Deletar
        holder.btnDelete.setOnClickListener(v -> {
            AppDatabase.getInstance(v.getContext()).artistaDao().delete(artista);
            listaArtistas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, listaArtistas.size());
            Toast.makeText(v.getContext(), "Artista Excluído!", Toast.LENGTH_SHORT).show();
        });

        // Ação de Editar
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddArtista.class);
            intent.putExtra("ID", artista.id);
            intent.putExtra("NOME", artista.nome);
            intent.putExtra("GENERO", artista.genero);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaArtistas.size();
    }

    public static class ArtistaViewHolder extends RecyclerView.ViewHolder {
        TextView textNome, textGenero;
        View btnEdit, btnDelete;

        public ArtistaViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.text_item_nome_artista);
            textGenero = itemView.findViewById(R.id.text_item_genero_artista);
            btnEdit = itemView.findViewById(R.id.btn_edit_artista_card);
            btnDelete = itemView.findViewById(R.id.btn_delete_artista_card);
        }
    }
}