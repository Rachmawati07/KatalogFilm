package com.rachma.katalogfilm.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rachma.katalogfilm.R
import com.google.gson.annotations.SerializedName

// Bagian class data

// Untuk mendeklarasikan entitas dengan nama tabel berupa movie
@Entity(tableName = "movie")
// Untuk mendeklarasikan class yang bernama movie
class Movie  {
    // Primary key berupa id yang memiliki tipe data int
    @PrimaryKey var id: Int
    // Memiliki nama serial dengan value title
    @SerializedName("title")
    // Mendeklarasikan variabel name dengan tipe data string yang bisa berisi null
    var name: String? = null
    // Mendeklarasikan nama serialnya dengan value berupa overview
    @SerializedName("overview")
    // Mendeklarasikan variabel yang bernama description dengan tipe data string yang bisa bernilai kosong
    var description: String? = null
    // Mendeklarasikan variabel img yang berasal dari drawable
    var img: Int?  = R.drawable.test
    // Mendeklarasikan variabel favorite dengan tipe data berupa boolean yang bernilai false
    var favorite : Boolean = false
    // Mendeklarasikan variabel poster_path dengan tipe data string yang bisa berisi null
    var poster_path: String? = null

    // Untuk memanggil fungsi to string untuk mengembalikan nilai nama
    override fun toString(): String {
        return name!!
    }

    // Untuk mendeklarasikan konstruktor yang berisi id, name, description, poster_path dan favorite
    constructor(id: Int, name: String?, description: String?,
                poster_path: String?,
                favorite : Boolean = false) {
        this.id  = id
        this.name = name
        this.description = description
        this.poster_path = poster_path
        this.favorite = favorite
    }

//    fun setImage(img: Int?){
//        this.img =  img
//    }
}