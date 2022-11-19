package com.example.kotlinsampleapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(var name: String, var description: String, var icon: String): Parcelable