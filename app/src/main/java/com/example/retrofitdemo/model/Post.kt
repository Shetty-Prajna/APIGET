package com.example.retrofitdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
 data class Post(
    var id: Int,
    var userId: Int,
    var title: String,
    var body: String

) :Parcelable


