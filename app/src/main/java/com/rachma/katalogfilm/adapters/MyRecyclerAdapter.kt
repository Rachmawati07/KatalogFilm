package com.rachma.katalogfilm.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachma.katalogfilm.R
import com.rachma.katalogfilm.api.Constants.Companion.Base_URL_Image
import com.rachma.katalogfilm.classes.Movie
import java.io.Serializable

// Bagian Adapter

// Untuk mendeklarasikan class yang bernama MyReyclerAdapter
// Dengan mendeklarasikan variabel content, list, listener
class MyRecyclerAdapter(private val context: Context, private var list: ArrayList<Movie>,
                        var listener: OnItemClickListener? = null)
    : Serializable, RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>(){

    // Untuk memanggil fungsi onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerHolder {
        // Untuk mendeklarasikan variabel itemView dan menginflate layout item_layout
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_layout, parent, false)
        // Untuk mengembalikan myRecyclerViewHolder
        return MyRecyclerHolder(itemView)
    }

    // Untuk memanggil fungsi getItemCount
    override fun getItemCount(): Int = list.size

    // Untuk menjalankan fungsi getItem pada tabel movie
    fun getItem(index: Int) : Movie
    {
        // Untuk mengembalikan list dalam bentuk index
        return list[index]
    }

    // Untuk menjalankan fungsi getList pada tabel movie dengan menggunakan array
    fun getList() : ArrayList<Movie>
    {
        // Untuk mengembalikan list
        return list
    }

    // Untuk menjalankan fungsi setData pada tabel movie dengan menggunakan array
    // Untuk memberitahu perubahan data
    fun setData(lst: ArrayList<Movie>)
    {
        list = lst
        notifyDataSetChanged()
    }

    // Untuk memanggil fungsi onBindViewHolder
    override fun onBindViewHolder(holder: MyRecyclerHolder, position: Int) {
        // Untuk mendeklarasikan variabel movie
        val movie: Movie = list[position]
        holder.bind(movie)
    }


    inner class MyRecyclerHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), View.OnClickListener
    {

        // Untuk mendeklarasikan variabel descriptionView, nameView, imageView, favImageView yang nilainya bisa berisi kosong
        private var descriptionView: TextView?= null
        private var nameView: TextView?= null
        private var imageView: ImageView?= null
        private var favImageView: ImageView?= null

        // Untuk menginisialisasi itemView ketika diberikan fungsi klik listener
        // Maka descriptionView, nameView, imageView, favImageView kan ditampilkan berdasarkan idnya
        init {
            itemView.setOnClickListener(this)
            descriptionView = itemView.findViewById(R.id.tv_description)
            nameView = itemView.findViewById(R.id.tv_name)
            imageView = itemView.findViewById(R.id.iv_poster)
            favImageView = itemView.findViewById(R.id.iv_fav)
        }

        // Untuk menjalanakn fungsi bind
        fun bind(movie: Movie) {
            // Jika movie merupakan favourite maka akan ditampilkan gambar yang berupa fav dari drawable
            if(movie.favorite)
                favImageView?.setImageResource(R.drawable.fav)
            // Dan jika tidak maka akan ditampilkan gambar yang berupa unfav dari drawable
            else
                favImageView?.setImageResource(R.drawable.unfav)
            //imageView?.setImageResource(movie.img!!)
            // Menjalankan library glide untuk meload gambar dan menjadikannya imageView
            Glide.with(context)
                    .load(Base_URL_Image + movie.poster_path)
                    .into(imageView!!)
            // Untuk mendapatkan data describtionView dan nameView
            descriptionView?.text = movie.description
            nameView?.text = movie.name

            favImageView?.setOnClickListener {
                listener!!.onImageClick(movie, favImageView!!)
            }
        }

        // Untuk memanggil fungsi onClict
        override fun onClick(v: View?) {
            // Untuk mendeklarasikan variabel posotion
            val position = adapterPosition
//            if (v?.getId() == R.id.imageView)
//            {
//                favImageView?.setImageResource(R.drawable.fav)
            if(position != RecyclerView.NO_POSITION){
                listener!!.onItemClick( position)
            }
        }
    }




}