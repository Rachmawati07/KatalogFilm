package com.rachma.katalogfilm.classes

import com.google.gson.annotations.SerializedName

// Untuk mendeklarasikan class yang bernama Image
class Image {
    // Untuk mendeklarasikan variabel id yang bisa berisi null
    var id: Int? = null
    // Mendeklarasikan nama serialnya dengan value pada file_path
    @SerializedName("file_path")
    // Untuk mendeklarasikan variabel yang bisa berisi null
    var path: String? = null

}