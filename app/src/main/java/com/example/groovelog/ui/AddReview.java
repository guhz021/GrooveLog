package com.example.groovelog.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.groovelog.R;
import com.example.groovelog.database.AppDatabase;
import com.example.groovelog.models.Artista;
import com.example.groovelog.models.Review;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SuppressLint("SetTextI18n")
public class AddReview extends AppCompatActivity {

    private Spinner spinnerArtistas;
    private EditText editAlbum, editData, editFaixas, editAnalise;
    private List<Artista> listaArtistasDoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        spinnerArtistas = findViewById(R.id.spinner_artistas);
        editAlbum = findViewById(R.id.edit_nome_album);
        editData = findViewById(R.id.edit_data_audicao);
        editFaixas = findViewById(R.id.edit_faixas_favoritas);
        editAnalise = findViewById(R.id.edit_analise);
        Button btnSalvar = findViewById(R.id.btn_salvar_review);

        configurarSpinner();
        configurarDataPicker();

        int idToEdit = getIntent().getIntExtra("ID", -1);
        if (idToEdit != -1) {
            preencherCamposEdicao();
            btnSalvar.setText("Atualizar Review");
        }

        btnSalvar.setOnClickListener(v -> validarESalvar(idToEdit));
        findViewById(R.id.btn_voltar_review).setOnClickListener(v -> finish());
    }

    private void configurarSpinner() {
        listaArtistasDoBanco = AppDatabase.getInstance(this).artistaDao().getAllArtistas();
        List<String> nomesArtistas = listaArtistasDoBanco.stream()
                .map(a -> a.nome).collect(Collectors.toList());

        if (nomesArtistas.isEmpty()) nomesArtistas.add("Cadastre um artista primeiro");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nomesArtistas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArtistas.setAdapter(adapter);
    }

    private void configurarDataPicker() {
        editData.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                // Corrigido o aviso do String.format adicionando Locale.getDefault()
                String dataFormatada = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
                editData.setText(dataFormatada);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void validarESalvar(int idToEdit) {
        if (listaArtistasDoBanco.isEmpty()) {
            Toast.makeText(this, "Erro: Nenhum artista cadastrado!", Toast.LENGTH_LONG).show();
            return;
        }

        String album = editAlbum.getText().toString();
        String data = editData.getText().toString();

        if (album.isEmpty()) {
            editAlbum.setError("O nome do álbum é obrigatório");
            return;
        }
        if (data.isEmpty()) {
            editData.setError("Selecione uma data");
            return;
        }

        Review r = new Review();
        if (idToEdit != -1) r.id = idToEdit;
        r.nomeAlbum = album;
        r.dataAudicao = data;
        r.faixasFavoritas = editFaixas.getText().toString();
        r.analise = editAnalise.getText().toString();

        r.artistaId = listaArtistasDoBanco.get(spinnerArtistas.getSelectedItemPosition()).id;

        if (idToEdit != -1) AppDatabase.getInstance(this).reviewDao().update(r);
        else AppDatabase.getInstance(this).reviewDao().insert(r);

        finish();
    }

    private void preencherCamposEdicao() {
        editAlbum.setText(getIntent().getStringExtra("ALBUM"));
        editData.setText(getIntent().getStringExtra("DATA"));
        editFaixas.setText(getIntent().getStringExtra("FAIXAS"));
        editAnalise.setText(getIntent().getStringExtra("ANALISE"));
    }
}