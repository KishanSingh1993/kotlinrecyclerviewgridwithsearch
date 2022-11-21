package com.example.kotlinsampleapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsampleapp.models.ContentData
import com.example.kotlinsampleapp.models.RootData
import com.example.kotlinsampleapp.utils.toDrawable
import java.util.*
import kotlin.collections.ArrayList

class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var image: ImageView = view.findViewById(R.id.img_social_card)
    var name: TextView = view.findViewById(R.id.txt_name)
}

class MainRecyclerViewAdapter(private var cards: List<ContentData>): RecyclerView.Adapter<CardViewHolder>(),Filterable {


    var countryFilterList = ArrayList<ContentData>()

    private lateinit var mContext: Context

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(newCards: List<ContentData>) {
        cards = newCards
        notifyDataSetChanged()
    }

    init {
        countryFilterList = cards as ArrayList<ContentData>
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_card, parent, false)
        return CardViewHolder(view)
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<ContentData>) {
        // below line is to add our filtered
        // list in our course array list.
        cards = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        val page = card


        holder.image.setImageResource(page.poster_image.toDrawable(holder.image.context))
        holder.name.text = page.name

    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = cards as ArrayList<ContentData>
                } else {
                    val resultList = ArrayList<ContentData>()
                    for (row in cards) {
//                        if (row.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))
//                        ) {
//
//                        }
                        resultList.add(row)
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<ContentData>
                notifyDataSetChanged()
            }

        }
    }
}