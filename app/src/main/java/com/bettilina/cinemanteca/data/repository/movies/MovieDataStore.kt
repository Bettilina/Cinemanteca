package com.bettilina.cinemanteca.data.repository.movies

import com.bettilina.cinemanteca.data.model.Movie

interface MovieDataStore {

    suspend fun getMovies(apiKey: String): List<Movie>

    suspend fun getMoviesByPage(apiKey: String, pageNumber: Int = 1): List<Movie>

    suspend fun getMoviewsByVoteAvg(apiKey: String, minVote: Int, maxVote: Int): List<Movie>
}