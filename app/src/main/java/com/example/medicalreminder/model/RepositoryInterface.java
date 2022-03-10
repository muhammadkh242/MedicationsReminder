package com.example.mvp_movies.model;

import androidx.lifecycle.LiveData;

import com.example.mvp_movies.network.NetworkDelegate;

import java.util.List;

public interface RepositoryInterface {

    void getAllMovies(NetworkDelegate networkDelegate);

    LiveData<List<MovieModel>> getStoredMovie();

    void insertMovie(MovieModel movie);
    void deleteMovie(MovieModel movie);
}
