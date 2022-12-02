package com.rachma.katalogfilm.api


import com.rachma.katalogfilm.classes.Image
import com.rachma.katalogfilm.classes.Movie
import com.rachma.katalogfilm.classes.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Untuk mendeklarasikan interface yang bernama SimpleApi
interface SimpleApi {
    // Untuk mendapatkan data dari api
    @GET("3/movie/550?api_key=461062f3ca455541c4c57750fcbf6759")
    // Untuk menjalankan fungsi getMovie dan memanggil tabel Movie
    fun getMovie(): Call<Movie>

    // Untuk mendapatkan data dari api
    @GET("3/movie/{key}/images?api_key=461062f3ca455541c4c57750fcbf6759")
    // Untuk menjalankan fungsi getImage dengan path yang berupa key dan movieId berupa in yang kemudian memanggil image
    fun getImage(@Path("key") movieId : Int ):Call<Image>

    // Untuk mendapatkan data dari api
    @GET("3/movie/{key}?api_key=461062f3ca455541c4c57750fcbf6759")
    // Untuk menjalankan fungsi getMovie dengan path yang berupa key dan movieId berupa in yang kemudian memanggil movie
    fun getMovie(@Path("key") movieId : Int ): Call<Movie>

    // Untuk mendapatkan data dari api
    @GET("3/search/movie?api_key=461062f3ca455541c4c57750fcbf6759")
    // Untuk menjalankan fungsi getMovieByName dengan query yang berupa query yang bertipe data string yang kemudian memanggil movieslist
    fun getMovieByName(@Query("query") query : String ): Call<MoviesList>

    // Untuk mendapatkan data dari api
    @GET("3/movie/popular?api_key=461062f3ca455541c4c57750fcbf6759")
    // Untuk menjalankan fungsi getPopularMovie yang kemudian memanggil movieslist
    fun getPopularMovie(): Call<MoviesList>
}