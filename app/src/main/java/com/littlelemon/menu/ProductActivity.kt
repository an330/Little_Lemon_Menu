package com.littlelemon.menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/*
class ProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productItem = intent.getParcelableExtra<ProductItem>("product_item")

        setContent {
            if (productItem != null) {
                ProductDetails(productItem)
            }
        }
    }
}*/
class ProductActivity : ComponentActivity() {

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_PRICE = "price"
        const val KEY_IMAGE = "image"
        const val KEY_CATEGORY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve product details from the Intent
        val title = intent.getStringExtra(KEY_TITLE)
        val price = intent.getDoubleExtra(KEY_PRICE, 0.0)
        val image = intent.getIntExtra(KEY_IMAGE, 0)
        val category = intent.getStringExtra(KEY_CATEGORY)

        // Create a ProductItem instance (optional)
        val productItem = if (title != null && category != null) {
            ProductItem(title, price, category, image)
        } else null

        // Set the UI content
        setContent {
            if (productItem != null) {
                ProductDetails(productItem)
            }
        }
    }
}

