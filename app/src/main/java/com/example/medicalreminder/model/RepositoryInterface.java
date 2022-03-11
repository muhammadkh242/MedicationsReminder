package com.example.medicalreminder.model;



public interface RepositoryInterface {

//    void getAllMovies(NetworkDelegate networkDelegate);
//
//    LiveData<List<MovieModel>> getStoredMovie();
//
//    void insertMovie(MovieModel movie);
//    void deleteMovie(MovieModel movie);

    void perForLogin(User user);
    void perForAuth(User user);
    void signOut();
}
