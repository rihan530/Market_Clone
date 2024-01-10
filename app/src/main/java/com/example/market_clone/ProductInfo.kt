package com.example.market_clone

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInfo
    (
    val productName: String,
    val image: Int,
    val location: String,
    val price: Int,
    var like: Int,
    var chat: Int,
    val nickname: String,
    val detailText: String,
    var isLiked: Boolean
) : Parcelable