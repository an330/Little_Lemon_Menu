package com.littlelemon.menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

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
}