package com.rachma.katalogfilm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rachma.katalogfilm.R
import com.rachma.katalogfilm.classes.Movie

// Untuk mendeklarasikan class yang bernama EmptyFragment
class EmptyFragment : Fragment(){
    // Untuk mendeklarasikan variabel yang bernama mContent
    private lateinit var mContext: Context
    // lateinit var myRecyclerAdapter: MyRecyclerAdapter
    companion object{
        // Untuk menjalankan fungsi untuk mendapatkan instance baru
        fun getNewInstance(args: Bundle): EmptyFragment {
            // Untuk mendeklarasikan variabel yang bernama emptyFragment
            val emptyFragment = EmptyFragment()
            emptyFragment.arguments = args
            // Untuk mengembalikan emptyFragment
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
        // Untuk mendeklarasikan variabel yang bernama view dan menginflate layut untuk empty_search
        val view = inflater.inflate(R.layout.empty_search, container, false)
        // Untuk mendeklarasikan text view dan akan ditampilkan berdasarkan id nya
        var textView = view.findViewById<TextView>(R.id.text_view)
        // Untuk menampilkan tulisan pada textView sesuai dengan yang dideklarasikan
        textView.setText("Request ${arguments?.get("bQueryString")} is nothing")
        // Untuk mengembalikan view
        return view
    }


}