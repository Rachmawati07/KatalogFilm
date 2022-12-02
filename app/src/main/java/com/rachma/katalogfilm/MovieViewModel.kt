package com.rachma.katalogfilm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rachma.katalogfilm.classes.Movie
import com.rachma.katalogfilm.room.AppDatabase
import com.rachma.katalogfilm.room.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

// Bagian ViewModel

// Untuk mendeklaraskan class yang bernama MovieViewModel yang menggunakan viewmodel
class MovieViewModel(application: Application): AndroidViewModel(application) {
    // Untuk mendeklarasikan variabel yang bernama repository
    private val repository: MovieRepository

    // Untuk mendeklarasikan variabel yang bernama _moview yang menggunakan live data
    private val _movies = MutableLiveData<List<Movie>>()
    // Untuk mendeklarasikan variabel readAll untuk mendapatkan semua data dari tabel movies yang menggunakan live data
    var readAll: LiveData<List<Movie>> =  _movies

    // Untuk menginisialisasi variabel noteDao untuk mendapatkan database dari movieDao dan menyimpannya di repository
    init {
        val noteDao = AppDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(noteDao)
    }

    // Untuk mendeklarasikan fungsi yang bernama makeSearch
    fun makeSearch(string: String) {
        // Untuk mendeklarasikan viewModelScope dan meluncurkan coroutine
        viewModelScope.launch{
            repository.makeSearch(_movies, string)
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama coroutine
    fun getPopular() {
        // Untuk mendeklarasikan viewModelScope dan meluncurkan dispatchers
        // Lalu mendapatkan data populer movies dari repository
        viewModelScope.launch(Dispatchers.IO){
            repository.getPopular(_movies)
//            repository.getAll()
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama getAll untuk mendapatkan semua data
    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll(_movies)
        }
    }

    // Untuk mendeklarasikan fungsi addMovie untuk menambahkan data pada tabel movie
    fun addMovie(movie: Movie){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovie(movie)
        }
    }

    // Untuk mendeklarasikan fungsi addMovie untuk menambahkan data pada tabel movie
    fun addMovies(lst: List<Movie>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMovies(lst)
        }
    }

    // Untuk menghapus data movie berdasarkan id
    fun deleteMovie(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMovie(id)
        }
    }

    // Untuk menghapus data movies sesuai yang diinginkan
    fun deleteMovies(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMovies()
        }
    }

}