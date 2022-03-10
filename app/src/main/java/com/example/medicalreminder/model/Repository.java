package com.example.mvp_movies.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mvp_movies.db.LocalSource;
import com.example.mvp_movies.network.NetworkDelegate;
import com.example.mvp_movies.network.RemoteSource;

import java.util.List;


public class Repository implements RepositoryInterface {

    private static Repository repo = null;
    LocalSource localSource;
    RemoteSource remoteSource;
    private Context context;


    private Repository(RemoteSource remoteSource, LocalSource localSource, Context context) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
        this.context = context;
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource, Context context) {
        if (repo == null) {
            repo = new Repository(remoteSource, localSource, context);
        }
        return repo;
    }

    @Override
    public void getAllMovies(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);
    }

    @Override
    public LiveData<List<MovieModel>> getStoredMovie() {
        return localSource.getAllStoredMovie();
    }

    @Override
    public void insertMovie(MovieModel movie) {
        localSource.insertMovie(movie);
    }

    @Override
    public void deleteMovie(MovieModel movie) {
        localSource.deleteMovie(movie);
    }
}
