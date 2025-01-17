package com.littlelemon.menu

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val title: String,
    val price: Double,
    val category: String,
    @DrawableRes val image: Int
) : Parcelable

data class Products(val items: List<ProductItem>)