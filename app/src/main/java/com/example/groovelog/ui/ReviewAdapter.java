package com.example.groovelog.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groovelog.R;
import com.example.groovelog.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> listaReviews;

    public ReviewAdapter(List<Review> listaReviews) {
        this.listaReviews = listaReviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = listaReviews.get(position);
        holder.textAlbum.setText(review.nomeAlbum);
        holder.textData.setText(review.dataAudicao);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailReview.class);
            intent.putExtra("ID", review.id); // Enviando o ID para permitir edição/deleção
            intent.putExtra("ALBUM", review.nomeAlbum);
            intent.putExtra("DATA", review.dataAudicao);
            intent.putExtra("FAIXAS", review.faixasFavoritas);
            intent.putExtra("ANALISE", review.analise);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaReviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView textAlbum, textData;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textAlbum = itemView.findViewById(R.id.text_item_nome_album);
            textData = itemView.findViewById(R.id.text_item_data_audicao);
        }
    }
}