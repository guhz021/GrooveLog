package com.example.groovelog.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Review;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        // Pegando os dados passados pela Intent
        int reviewId = getIntent().getIntExtra("ID", -1);
        String album = getIntent().getStringExtra("ALBUM");
        String data = getIntent().getStringExtra("DATA");
        String faixas = getIntent().getStringExtra("FAIXAS");
        String analise = getIntent().getStringExtra("ANALISE");

        // Preenchendo a tela
        TextView textAlbum = findViewById(R.id.detail_nome_album);
        TextView textData = findViewById(R.id.detail_data);
        TextView textFaixas = findViewById(R.id.detail_faixas);
        TextView textAnalise = findViewById(R.id.detail_analise);

        if (album != null) textAlbum.setText(album);
        if (data != null) textData.setText("Ouvido em: " + data);
        if (faixas != null) textFaixas.setText(faixas);
        if (analise != null) textAnalise.setText(analise);

        // Botão Voltar
        findViewById(R.id.btn_voltar_detail).setOnClickListener(v -> finish());

        // Botão Deletar
        findViewById(R.id.btn_delete_review).setOnClickListener(v -> {
            Review r = new Review();
            r.id = reviewId;
            AppDatabase.getInstance(this).reviewDao().delete(r);
            Toast.makeText(this, "Review excluída com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Fecha a tela e volta pra lista
        });

        // Botão Editar
        findViewById(R.id.btn_edit_review).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddReview.class);
            intent.putExtra("ID", reviewId);
            intent.putExtra("ALBUM", album);
            intent.putExtra("DATA", data);
            intent.putExtra("FAIXAS", faixas);
            intent.putExtra("ANALISE", analise);
            startActivity(intent);
            finish(); // Fecha a tela de detalhes
        });
    }
}