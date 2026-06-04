package com.example.groovelog.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.groovelog.models.Review;

@Dao
public interface ReviewDao {
    @Insert
    void insert(Review review);

    @androidx.room.Update
    void update(Review review);

    @androidx.room.Delete
    void delete(Review review);

    @Query("SELECT * FROM tabela_reviews")
    List<Review> getAllReviews();

    @Query("SELECT * FROM tabela_reviews ORDER BY id DESC LIMIT 3")
    List<Review> getReviewsRecentes();
}