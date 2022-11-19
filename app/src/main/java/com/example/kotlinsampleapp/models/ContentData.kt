package com.example.kotlinsampleapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentData (

    var name : String,
    var poster_image : String
): Parcelable
