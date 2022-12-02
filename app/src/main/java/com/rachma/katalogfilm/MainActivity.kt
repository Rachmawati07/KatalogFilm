package com.rachma.katalogfilm

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rachma.katalogfilm.adapters.MyRecyclerAdapter
import com.rachma.katalogfilm.adapters.OnItemClickListener
import com.rachma.katalogfilm.api.Constants
import com.rachma.katalogfilm.api.SimpleApi
import com.rachma.katalogfilm.classes.Movie
import com.rachma.katalogfilm.classes.MoviesList
import com.rachma.katalogfilm.fragments.EmptyFragment
import com.rachma.katalogfilm.fragments.OutInternetFragment
import com.rachma.katalogfilm.fragments.RecyclerFragment
import com.rachma.katalogfilm.room.AppDatabase
import com.rachma.katalogfilm.room.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Bagian untuk class activity untuk view ke AcitivityMain

// Untuk membuat class yang bernama MainActivity
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, OnItemClickListener {
    // Mendeklarasikan variabel yang bernama myRecyclerAdapter
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter
    // Untuk mendeklarasikan variabel yang bernama simpleApi
    lateinit var simpleApi: SimpleApi
    // Untuk mendeklarasikan variabel yang bernama movieViewModel
    lateinit var movieViewModel: MovieViewModel
    // Untuk mendeklarasikan variabel refreshString
    var refreshString = ""

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Untuk mendapatkan movieViewModel dan membaca datanya
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.readAll.observe(this, Observer {

            // Jika datanya tidak kosong, maka akan menampilkan data dalam bentuk listaray
            if (it != null) {
                if (it.isNotEmpty()) {
                    myRecyclerAdapter.setData(ArrayList(it))

                    // Untuk mendeklarasikan variabel bundle
                    var bundle = Bundle()
                    // Untuk meletakkan serializable yang berupa bRecyclerAdapter
                    bundle.putSerializable("bRecyclerAdapter", myRecyclerAdapter)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.place_holder, RecyclerFragment.getNewInstance(bundle), "recyclerFragment")
                        .commit()
                }
                // Dan jika data kosong maka akan meletakkan string
                else
                {
                    var bundle = Bundle()
                    bundle.putString("bQueryString", refreshString)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.place_holder, EmptyFragment.getNewInstance(bundle))
                        .commit()
                }
            }
            else
            {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.place_holder, OutInternetFragment())
                    .commit()
            }
        })

        // Untuk mendapatkan data movie populer dari movieViewModel
        movieViewModel.getPopular()

        // Untuk mendeklarasikan variabel retrofit dan menjalankan builder
        // Untuk mengakses url dan membuat GsonConverterFactory
        var retrofit : Retrofit=Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Untuk menjalankan library retrofit untuk membuat class java simpleApi
        simpleApi = retrofit.create(SimpleApi::class.java)

        // Untuk mendeklarasikan myRecyclerAdapter dan menampilkan datanya dalam bentuk list array
        myRecyclerAdapter = MyRecyclerAdapter(this, ArrayList<Movie>(), this)

        // Untuk mendeklarasikan variabel swipeContainer
        var swipeContainer = findViewById<SwipeRefreshLayout>(R.id.swipe_container)

        swipeContainer.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            movieViewModel.makeSearch(refreshString)
            swipeContainer.setRefreshing(false)
        })
    }

    // Untuk memanggil fungsi onCreateOptionsMenu dan menginflate layout pada main_menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        // Untuk mendeklarasikan variabel search dan ditampilkan dalam bentuk menu_search
        val search = menu?.findItem(R.id.menu_search)
        // Untuk mendeklarasikan variabel searchView dan ketika dijalankan aksi maka akan menjalankan query
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    // Untuk menjalankan fungsi onQueryTextSubmit dengan tipe data boolean
    override fun onQueryTextSubmit(query: String?): Boolean {
        refreshString = query!!
        movieViewModel.makeSearch(refreshString)
        return true
    }

    // Untuk menjalankan fungsi onQueryTextChange dengan tipe data boolean
    override fun onQueryTextChange(query: String?): Boolean {
        refreshString = query!!
        movieViewModel.makeSearch(refreshString)
        return true
    }

    // Untuk memanggil fungsi onItemClict dan menampilkan pesan dalam bentuk toast
    override fun onItemClick( position: Int) {
        Toast.makeText(this, "${myRecyclerAdapter!!.getItem(position).toString()}", Toast.LENGTH_SHORT).show()
    }

    // Untuk memanggil fungsi onImageClick
    override fun onImageClick(movie: Movie, favImageView: ImageView) {
        // Jika movienya termasuk favorite maka akan menampilkan gambar fav dari drawable
        if (!movie.favorite) {
            movie.favorite = true
            movieViewModel.addMovie(movie)
            favImageView?.setImageResource(R.drawable.fav)
        }
        // Jika movienya tidak termasuk favurite maka akan menampilkan gambar unfav dari drawable
        else {
            movieViewModel.deleteMovie(movie.id)
            movie.favorite = false
            favImageView?.setImageResource(R.drawable.unfav)
        }
    }
}