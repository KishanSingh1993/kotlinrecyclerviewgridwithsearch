package com.example.kotlinsampleapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentItemsData(var content : List<ContentData>): Parcelable
