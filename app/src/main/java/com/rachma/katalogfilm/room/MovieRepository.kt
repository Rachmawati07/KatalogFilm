package com.rachma.katalogfilm.room


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rachma.katalogfilm.R
import com.rachma.katalogfilm.api.Constants
import com.rachma.katalogfilm.api.SimpleApi
import com.rachma.katalogfilm.classes.Movie
import com.rachma.katalogfilm.classes.MoviesList
import com.rachma.katalogfilm.fragments.EmptyFragment
import com.rachma.katalogfilm.fragments.OutInternetFragment
import com.rachma.katalogfilm.fragments.RecyclerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Untuk mendeklarasikan class yang bernama MovieRepository dan mendeklarasikan variabel bernama localDataSource
class MovieRepository(private val localDataSource: MovieDao) {
    // Untuk mendeklarasikan variabel simpleApi
    private val simpleApi: SimpleApi
//    private val remoteDataSource: MovieRemoteDataSource


    // Untuk menginisialisasi dan menjalankan libray retrofit
    // Dan menggunakan method Builder
    // Untuk mengakses url dan membuat GsonConverterFactory
    init {
        var retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        // Untuk menjalankan library retrofit untuk membuat class java retrofit
        simpleApi = retrofit.create(SimpleApi::class.java)
    }

    // Untuk menjalankan fungsi addMovie dan menambahkan movie pada localDataSource
    fun addMovie(el: Movie) {
        localDataSource.insertMovie(el)
//        print(el)
//        Log.d("TAG", el.toString())
    }

    // Untuk menjalankan fungsi addMovies dan menambahkan movie pada localDataSource
    fun addMovies(lst: List<Movie>) {
        for (elem in lst)
            localDataSource.insertMovie(elem);
    }

    // Untuk menjalankan fungsi deleteMovies dan mengembalikannya pada localDataSources untuk menghapus semua data
    fun deleteMovies() {
        return localDataSource.deleteAll()
    }

    // Untuk menjalankan fungsi deleteMovie dan mengembalikannya pada localDataSources untuk menghapus data berdasarkan id
    fun deleteMovie(id: Int) {
        return localDataSource.delete(id)
    }

    // Untuk menjalankan fungsi findMovie dan mengembalikannya pada localDataSources untuk menemukan  data berdasarkan id
    fun findMovie(id: Int): Movie? {
        return localDataSource.findMovie(id)
    }

    // Untuk mendapatkan semua data movie menggunakan live data
    fun getAll(_movies: MutableLiveData<List<Movie>>): LiveData<List<Movie>> {
        return localDataSource.getAll()
    }

    // Untuk menjalankan fungsi getPopular dengan menggunakan live data
    fun getPopular(liveData: MutableLiveData<List<Movie>>){
        // Untuk mendeklarasikan variabel call untuk memanggil simpleApi untuk mendapatkan movie yang populer
        var call: Call<MoviesList> = simpleApi.getPopularMovie()
        movieAsyncCall(call,  liveData)
    }

    // Untuk menjalankan fungsi movieAsyncCall dengan menggunakan live data
    fun movieAsyncCall(call: Call<MoviesList>, liveData: MutableLiveData<List<Movie>>,  str: String = ""){
        call.enqueue(object : Callback<MoviesList> {
            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                liveData.value = null
            }

            // Untuk memanggil fungsi onRespinse
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                // Untuk mendeklarasikan variabel moview dan mendapatkan hasil
                var movies = response.body()!!.results
                Log.d("tag", movies.toString())
                // Jika movies sama dengan null maka nilai dari live data juga sama dengan null
                if (movies == null) {
                    liveData.value = null
                }
                // Jika movies btidak sama dengan null
                else {
                    // Namun movies kosong maka nilai live data berupa array list
                    if(movies.isEmpty())
                        liveData.value = ArrayList()
                    // Namun jika sebaliknya akan akan menampilkan live data dan nilainya
                    else
                        GlobalScope.launch {
                            liveData.postValue(checkMoviesInDb(movies))
                        }
                }
            }
        })
    }

    // Untuk menjalankan fungsi checkMoviewsInDb yang berupa listArray
    private fun checkMoviesInDb(lst: List<Movie>): ArrayList<Movie> {
            // Untuk mendeklarasikan variabel resLst
            var resLst = ArrayList<Movie>()
            // Untuk menjalankan perulangan
            for (i in 0..lst.size - 1) {
                // Untuk mendeklarasikan variabel movie
                var movie = localDataSource.findMovie(lst[i].id)

                // Jika movie tidak sama dengan null maka akan menambahkan movie
                if (movie != null)
                    resLst.add(movie)
                else
                    resLst.add(lst[i])
            }
        return resLst
    }

    // Untuk menjalankan fungsi makeSearch dengan ,enggunakan live data
    fun makeSearch(liveData: MutableLiveData<List<Movie>>, str: String) {
        // Jika str sama dengan kosong maka akan mendapatkan datanya dengan live data
        if (str =="" || str ==" ")
            getPopular(liveData)
        // Namun jika salah maka akan memanggil simpleApi
        else {
            var call: Call<MoviesList> = simpleApi.getMovieByName(str)
            movieAsyncCall(call, liveData,str)
        }
    }
}