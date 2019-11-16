package com.bettilina.cinemanteca.data.repository

import com.bettilina.cinemanteca.data.model.Genre
import com.bettilina.cinemanteca.data.model.Movie
import com.bettilina.cinemanteca.data.model.Review

interface MovieSourceRepository {

    suspend fun getMovies(): List<Movie>

    suspend fun getMoviesByPage(page: Int): List<Movie>

    suspend fun getMoviewsByVoteAvg(minVote: Int, maxVote: Int): List<Movie>

    suspend fun getMoviesGenres():List<Genre>

    suspend fun getMoviesBySearch(apiKey: String, txtSearch: String): List<Movie>

    suspend fun getMovieReviews(apiKey: String, id: Int): List<Review>
}