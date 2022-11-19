package com.example.kotlinsampleapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageData(
                    var title : String,
                    var total_content_items : String,
                    var page_num : String,
                    var page_size : String,
                    var content_items : ContentItemsData): Parcelable
