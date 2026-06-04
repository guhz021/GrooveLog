package com.example.groovelog.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabela_artistas")
public class Artista {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;
    public String genero;
}