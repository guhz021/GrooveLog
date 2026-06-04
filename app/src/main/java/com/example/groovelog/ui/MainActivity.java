package com.example.groovelog.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Artista;
import com.example.groovelog.models.Review;

import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerArtistasRecentes;
    private RecyclerView recyclerReviewsRecentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Configurando a Lista de Artistas (RecyclerView)
        recyclerArtistasRecentes = findViewById(R.id.recycler_artistas_recentes);
        recyclerArtistasRecentes.setLayoutManager(new LinearLayoutManager(this));

        // 2. Configurando a Lista de Reviews (RecyclerView)
        recyclerReviewsRecentes = findViewById(R.id.recycler_reviews_recentes);
        recyclerReviewsRecentes.setLayoutManager(new LinearLayoutManager(this));

        // --- BOTÕES DE ADICIONAR ---
        Button btnNovoArtista = findViewById(R.id.btn_novo_artista);
        btnNovoArtista.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddArtista.class)));

        Button btnNovaReview = findViewById(R.id.btn_nova_review);
        btnNovaReview.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddReview.class)));

        // --- BOTÕES VER TODOS (Agora abrindo as telas completas!) ---
        Button btnVerArtistas = findViewById(R.id.btn_ver_todos_artistas);
        btnVerArtistas.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListArtistas.class)));

        Button btnVerReviews = findViewById(R.id.btn_ver_todas_reviews);
        btnVerReviews.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListReviews.class)));
    }

    // O onResume roda sempre que a tela volta a ficar visível
    @Override
    protected void onResume() {
        super.onResume();
        atualizarListas();
    }

    private void atualizarListas() {
        // --- Atualiza Artistas ---
        List<Artista> artistas = AppDatabase.getInstance(this).artistaDao().getArtistasRecentes();
        TextView txtArtistasVazio = findViewById(R.id.text_artistas_vazio);

        if (artistas.isEmpty()) txtArtistasVazio.setVisibility(View.VISIBLE);
        else txtArtistasVazio.setVisibility(View.GONE);

        recyclerArtistasRecentes.setAdapter(new ArtistaAdapter(artistas));

        // --- Atualiza Reviews ---
        List<Review> reviews = AppDatabase.getInstance(this).reviewDao().getReviewsRecentes();
        TextView txtReviewsVazio = findViewById(R.id.text_reviews_vazio);

        if (reviews.isEmpty()) txtReviewsVazio.setVisibility(View.VISIBLE);
        else txtReviewsVazio.setVisibility(View.GONE);

        recyclerReviewsRecentes.setAdapter(new ReviewAdapter(reviews));
    }
}