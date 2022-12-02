package com.rachma.katalogfilm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachma.katalogfilm.R
import com.rachma.katalogfilm.adapters.MyRecyclerAdapter

// Untuk mendeklarasikan class yang bernama RecyclerFragment
class RecyclerFragment : Fragment(){
    // Untuk mendeklarasikan variabel yang bernama recyclerView
    lateinit var recyclerView : RecyclerView
    // Untuk mendeklarasikan variabel mContext
    private lateinit var mContext: Context
    // Untuk mendeklarasikan variabel myRecyclerAdapter
    private var myRecyclerAdapter : MyRecyclerAdapter? = null;

    companion object{
        // Untuk menjalankan fungsi untuk mendapatkan instance baru untuk RecyclerFragment
        fun getNewInstance(args: MyRecyclerAdapter): RecyclerFragment {
            // Untuk mendeklarasikan variabel yang bernama recyclerFragment
            val recyclerFragment = RecyclerFragment()
            recyclerFragment.arguments?.putSerializable("recyclerAdapter", args)
            return recyclerFragment
        }

        // Untuk manjalankan fungsi getNewInstance
        fun getNewInstance(args: Bundle): RecyclerFragment {
            // Untuk mendeklarasikan variabel recyclerFragment
            val recyclerFragment = RecyclerFragment()
            recyclerFragment.arguments = args
            return recyclerFragment
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
        // Untuk mendeklarasikan variabel yang bernama view dan menginflate layut untuk recycler_layout
        val view = inflater.inflate(R.layout.recycler_layout, container, false)
        // Untuk mendeklarasikan recyclerView dan ditampilkan berdasarkan rv_movie
        recyclerView = view.findViewById(R.id.rv_movie)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Untuk mendeklarasikan variabel myRecyclerAdapter dan mendapatkan serial dari kunci bRecyclerAdapter
        var myRecyclerAdapter =  arguments?.getSerializable("bRecyclerAdapter") as MyRecyclerAdapter?
        this.myRecyclerAdapter = myRecyclerAdapter

        recyclerView.adapter = this.myRecyclerAdapter;
        return view
    }

}