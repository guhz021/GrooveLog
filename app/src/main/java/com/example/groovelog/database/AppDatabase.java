package com.example.groovelog.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.groovelog.models.Artista;
import com.example.groovelog.models.Review;

@Database(entities = {Artista.class, Review.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArtistaDao artistaDao();
    public abstract ReviewDao reviewDao();

    private static AppDatabase INSTANCE;

    // Padrão Singleton para garantir que só exista uma instância do banco aberta
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "groovelog_db")
                    // Permite rodar na thread principal para facilitar a entrega acadêmica
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}