package com.example.kotlinsampleapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinsampleapp.models.ContentData
import com.example.kotlinsampleapp.utils.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), RecyclerItemClickListener.OnRecyclerClickListener {

    private val TAG = "MainActivity"
    private val viewModel: MainViewModel by inject()
    private var recyclerViewAdapter = MainRecyclerViewAdapter(ArrayList())
    lateinit var itemList: ArrayList<ContentData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemList = ArrayList()
        recycler_view.layoutManager = GridLayoutManager(this,3)
        recycler_view.adapter = recyclerViewAdapter
        //recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_view, this))

        observeFromViewModal()
        viewModel.fetchCards(this)
    }

    private fun observeFromViewModal() {
        viewModel.cards.observe(this) { rootdata ->
            recyclerViewAdapter.loadData(rootdata.page.content_items.content)
        }
    }

    override fun onItemPress(view: View, position: Int) {
        Log.d(TAG, "The tapped position: $position")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater

        inflater.inflate(R.menu.menu_main, menu)

        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        val searchView: SearchView = searchItem.getActionView() as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {

                //filter(msg)
                recyclerViewAdapter.filter.filter(msg)
                return false
            }
        })
        return true
    }

    @SuppressLint("DefaultLocale")
    private fun filter(text: String) {

        val filteredlist: ArrayList<ContentData> = ArrayList()

        for (item in itemList) {

            if (item.name.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {

            recyclerViewAdapter = MainRecyclerViewAdapter(itemList)
            recycler_view.adapter = recyclerViewAdapter
            recyclerViewAdapter.filter.filter(text)
        }
    }

}