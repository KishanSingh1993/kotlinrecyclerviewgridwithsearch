package com.example.kotlinsampleapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinsampleapp.models.RootData
import com.example.kotlinsampleapp.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(private val utils: Utils): ViewModel() {
    private val mutableCards = MutableLiveData<RootData>()
    val cards: LiveData<RootData> get() = mutableCards

    fun fetchCards(context: Context) {
        val jsonString = utils.getJson(context, "list1.json")

        val gson = Gson()
        val listCardType = object: TypeToken<RootData>() {}.type

        val cards = gson.fromJson<RootData>(jsonString, RootData::class.java)
        mutableCards.value = cards
    }
}