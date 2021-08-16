package com.manegow.iomessenger.parcelables

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookParcelable(
    val isbn: String,
    val title: String,
    val image: String,
    val description: String,
    val author: String
): Parcelable
