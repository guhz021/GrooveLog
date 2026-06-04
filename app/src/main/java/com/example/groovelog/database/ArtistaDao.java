package com.example.groovelog.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.groovelog.models.Artista;

@Dao
public interface ArtistaDao {
    @Insert
    void insert(Artista artista);

    @androidx.room.Update
    void update(Artista artista);

    @androidx.room.Delete
    void delete(Artista artista);

    @Query("SELECT * FROM tabela_artistas")
    List<Artista> getAllArtistas();

    @Query("SELECT * FROM tabela_artistas ORDER BY id DESC LIMIT 3")
    List<Artista> getArtistasRecentes();
}