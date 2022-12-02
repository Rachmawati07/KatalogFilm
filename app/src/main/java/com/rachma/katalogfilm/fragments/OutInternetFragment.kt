package com.rachma.katalogfilm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rachma.katalogfilm.R
import com.rachma.katalogfilm.classes.Movie

// Untuk mendeklarasikan class yang bernama OutInternetFragment
class OutInternetFragment : Fragment(){
    // Untuk mendeklarasikan variabel yang bernama mContent
    private lateinit var mContext: Context
    companion object{
        // Untuk menjalankan fungsi untuk mendapatkan instance baru
        fun getNewInstance(args: Bundle): OutInternetFragment {
            // Untuk mendeklarasikan variabel yang bernama emptyFragment
            val emptyFragment = OutInternetFragment()
            emptyFragment.arguments = args
            return emptyFragment
        }
    }

    // Untuk memanggil fungsi onAttach yang berisikan context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    // Untuk memanggil fungsi onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Untuk mendeklarasikan view untuk menginflate layout out_internet_layout
        val view = inflater.inflate(R.layout.out_internet_layout, container, false)
        // Untuk mengembalikan view
        return view
    }

}