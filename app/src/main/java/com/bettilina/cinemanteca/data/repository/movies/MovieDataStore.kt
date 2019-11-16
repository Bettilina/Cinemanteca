package com.bettilina.cinemanteca.data.repository.movies

import com.bettilina.cinemanteca.data.model.Genre
import com.bettilina.cinemanteca.data.model.Movie
import com.bettilina.cinemanteca.data.model.Review

interface MovieDataStore {

    suspend fun getMovies(apiKey: String): List<Movie>

    suspend fun getMoviesByPage(apiKey: String, pageNumber: Int = 1): List<Movie>

    suspend fun getMoviesByVoteAvg(apiKey: String, minVote: Int, maxVote: Int): List<Movie>

    suspend fun getGenres(apiKey: String): List<Genre>

    suspend fun getMoviesBySearch(apiKey: String, txtSearch: String): List<Movie>

    suspend fun getMovieReviews(apiKey: String, id: Int): List<Review>
}