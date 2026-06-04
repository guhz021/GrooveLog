package com.example.groovelog.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Review;

import java.util.List;

public class ListReviews extends AppCompatActivity {

    private RecyclerView recyclerTodasReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reviews);

        // 1. Encontra e configura o RecyclerView
        recyclerTodasReviews = findViewById(R.id.recycler_todas_reviews);
        recyclerTodasReviews.setLayoutManager(new LinearLayoutManager(this));

        // 2. Configura o botão de voltar
        findViewById(R.id.btn_voltar_list_reviews).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Puxa TODAS as reviews do banco de dados
        List<Review> todasReviews = AppDatabase.getInstance(this).reviewDao().getAllReviews();

        // Aproveita o ReviewAdapter que já tem o clique configurado para abrir os Detalhes
        ReviewAdapter adapter = new ReviewAdapter(todasReviews);
        recyclerTodasReviews.setAdapter(adapter);
    }
}