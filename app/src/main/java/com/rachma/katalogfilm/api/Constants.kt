package com.rachma.katalogfilm.api

// Bagian untuk request ke web service

// Untuk mendeklarasikan class yang bernama Constants
class Constants {
    companion object{
        // Mendeklarasikan variabel yang bernama BASE_URL untuk mengakses api
        const val BASE_URL = "https://api.themoviedb.org/"

        // Bagian untuk mengakses url gambar
        // Untuk mendeklarasikan variabel yang bernama Base_URL_Image untuk mendapatkan data gambar dari internet
        const val Base_URL_Image = "https://image.tmdb.org/t/p/w200"
    }
}