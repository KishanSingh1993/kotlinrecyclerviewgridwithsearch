package com.example.kotlinsampleapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsampleapp.models.ContentData
import com.example.kotlinsampleapp.models.RootData
import com.example.kotlinsampleapp.utils.toDrawable

class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var image: ImageView = view.findViewById(R.id.img_social_card)
    var name: TextView = view.findViewById(R.id.txt_name)
}

class MainRecyclerViewAdapter(private var cards: List<ContentData>): RecyclerView.Adapter<CardViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(newCards: List<ContentData>) {
        cards = newCards
        notifyDataSetChanged()
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
}