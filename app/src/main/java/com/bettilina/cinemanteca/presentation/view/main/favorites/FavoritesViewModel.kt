package com.bettilina.cinemanteca.presentation.view.main.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bettilina.cinemanteca.data.model.Movie
import com.bettilina.cinemanteca.data.repository.MovieSourceRepository
import com.bettilina.cinemanteca.data.repository.movies.DatabaseMovieDataStore
import com.bettilina.cinemanteca.utils.OrderCriterial
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FavoritesViewModel(private val dbDataStore: DatabaseMovieDataStore): ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    //Declare LiveData attributes
    val movies: LiveData<List<Movie>>
        get() = localMovies

    val isLoading: LiveData<Boolean>
        get() = localIsLoading

    //Create MutableLiveData attributes
    private val localMovies = MutableLiveData<List<Movie>>()
    private val localIsLoading = MutableLiveData<Boolean>()

    fun loadFavMovies(){
        localIsLoading.postValue(true)
        launch(Dispatchers.IO){
            //Delay so the user can see the loading icon - LOL!
            kotlinx.coroutines.delay(1000)
            try {
                val movies = dbDataStore.getFavoriteMovies()
                localMovies.postValue(movies)
            } catch (exception: Exception){

            } finally {
                localIsLoading.postValue(false)
            }
        }
    }

    fun addFavoriteMovie(movieId: Int){
        launch(Dispatchers.IO){
            try {
                dbDataStore.addFavorite(movieId)
            } catch (error: java.lang.Exception){
                Log.d("ADD_FAVS_EXC", "Exception when adding movie to favorites: " + error)
            }
        }
    }

    fun removeFavoriteMovie(movieID: Int){
        launch(Dispatchers.IO){
            try {
                dbDataStore.removeFavorite(movieID)
                loadFavMovies()
            } catch (error: java.lang.Exception){
                Log.d("REMOVE_FAV_EXC", "Exception when removing movie from favorites: " + error)
            }
        }
    }

    fun orderView(criterial : OrderCriterial){
        val adapt = movies.value
        adapt?.let{
            var sortedList = when(criterial){
                OrderCriterial.AVERAGE -> adapt.sortedByDescending{ movie ->movie.voteAverage.toString() }
                OrderCriterial.TITLE -> adapt.sortedBy{ movie ->movie.title }
            }
            localMovies.postValue(sortedList)
        }
    }
}