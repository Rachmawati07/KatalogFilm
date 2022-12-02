package com.rachma.katalogfilm.adapters

import android.view.View
import android.widget.ImageView
import com.rachma.katalogfilm.classes.Movie

// Untuk mendeklarasikan interface yang bernama OnItemClickListener
interface OnItemClickListener{
    // Untuk mendeklarasikan fungsi yang bernama OnItemClick pada position int
    fun onItemClick(position: Int)
    // Untuk mendeklarasikan fungsi yang bernama onImageClick untuk movie dan ImageView
    fun onImageClick(movie: Movie, favImageView: ImageView)
}