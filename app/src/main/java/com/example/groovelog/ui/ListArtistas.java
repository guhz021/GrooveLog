package com.example.groovelog.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Artista;

import java.util.List;

public class ListArtistas extends AppCompatActivity {

    private RecyclerView recyclerTodosArtistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artistas);

        // 1. Encontra e configura o RecyclerView
        recyclerTodosArtistas = findViewById(R.id.recycler_todos_artistas);
        recyclerTodosArtistas.setLayoutManager(new LinearLayoutManager(this));

        // 2. Configura o botão de voltar
        findViewById(R.id.btn_voltar_list_artistas).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Puxa TODOS os artistas do banco de dados (o método sem LIMIT)
        List<Artista> todosArtistas = AppDatabase.getInstance(this).artistaDao().getAllArtistas();

        // Usa o mesmo Adapter que criamos antes para desenhar os cards na tela
        ArtistaAdapter adapter = new ArtistaAdapter(todosArtistas);
        recyclerTodosArtistas.setAdapter(adapter);
    }
}