package com.rachma.katalogfilm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rachma.katalogfilm.classes.Movie

// Untuk mendeklarasikan Dao
@Dao
// Mendeklarasikan interface yang bernama MovieDao
interface MovieDao {
    // Untuk mendeklarasikan query yang menyeleksi semua data dari tabel movie dimana name ada tabel movie seperti yang dicari pada searchQuery
    @Query("SELECT * FROM movie WHERE movie.name LIKE  :searchQuery")
    // Untuk mendeklarasikan fungsi getSearchResult sebagai hasil dan akan menggunakan live data untuk menampilkannya
    fun getSearchResults(searchQuery : String) : LiveData<List<Movie>>

    // Untuk mendeklaraikan query yang menyeleksi semua data dari tabel movie
    @Query("SELECT * FROM movie")
    // Untuk mendeklarasikan fungsi yang bernama getAll untuk mendapatkan semua hasilnya dan menggunakan live data
    fun getAll(): LiveData<List<Movie>>

    // Untuk menambahkan data
    // Menjalankan fungsi insertMovie untuk menambahkan data kedalam tabel movie
    @Insert
    fun insertMovie(movie: Movie)

    // Untuk menghapus data dari tabel movie
    // Menjalankan fungsi deleteAll() untuk menghapus semua data
    @Query("DELETE FROM movie")
    fun deleteAll()

    // Untuk menjalankan query yang menghapus data dari tabel movie dimana id pada movie sama dengan id
    @Query("DELETE FROM movie WHERE movie.id = :id")
    // Untuk mendeklarasikan fungsi yang bernama delete
    fun delete(id : Int)

    // Untuk menjalankan query yang menampilkan semua data dimana id pada movie sama dengan id
    @Query("SELECT * FROM movie WHERE movie.id = :id")
    // Menjalankan fungsi findMovie
    fun findMovie(id : Int): Movie? //: List<Movie>

//    @Query("SELECT CASE movie.id\n" +
//            "    WHEN :idQuery THEN 1\n" +
//            "    else 0\n" +
//            "END\n" +
//            "FROM movie")
//    fun IsfindMovie(idQuery : Int): Boolean
}