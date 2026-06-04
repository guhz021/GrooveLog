package com.example.groovelog.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabela_reviews")
public class Review {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int artistaId; // Chave estrangeira
    public String nomeAlbum;
    public String dataAudicao;
    public String faixasFavoritas; // <-- Esta é a linha que faltava!
    public String analise;
}