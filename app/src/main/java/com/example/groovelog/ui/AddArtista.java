package com.example.groovelog.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Artista;

@SuppressLint("SetTextI18n")
public class AddArtista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artista);

        EditText editNome = findViewById(R.id.edit_nome_artista);
        EditText editGenero = findViewById(R.id.edit_genero_artista);
        Button btnSalvar = findViewById(R.id.btn_salvar_artista);

        int idToEdit = getIntent().getIntExtra("ID", -1);
        if (idToEdit != -1) {
            editNome.setText(getIntent().getStringExtra("NOME"));
            editGenero.setText(getIntent().getStringExtra("GENERO"));
            btnSalvar.setText("Atualizar Artista");
        }

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString();
            if (nome.isEmpty()) {
                editNome.setError("O nome do artista é obrigatório");
                return;
            }

            Artista a = new Artista();
            if (idToEdit != -1) a.id = idToEdit;
            a.nome = nome;
            a.genero = editGenero.getText().toString();

            if (idToEdit != -1) AppDatabase.getInstance(this).artistaDao().update(a);
            else AppDatabase.getInstance(this).artistaDao().insert(a);

            finish();
        });

        findViewById(R.id.btn_voltar_artista).setOnClickListener(v -> finish());
    }
}