package com.artonov.betteryou.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val bookName: String,
    val bookAuthor: String,
    val bookYear: String,
    val bookDescription: String,
    val bookImages: String,
    val bookLink: String
): Parcelable
